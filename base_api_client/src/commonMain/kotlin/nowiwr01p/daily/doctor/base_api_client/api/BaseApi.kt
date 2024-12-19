package nowiwr01p.daily.doctor.base_api_client.api

import com.nowiwr01p.model.api.errors.AppUiError
import com.nowiwr01p.model.api.errors.HttpClientError.ExpectedError
import com.nowiwr01p.model.api.errors.HttpClientError.NoErrorExpected
import com.nowiwr01p.model.api.errors.HttpClientError.UnexpectedError
import com.nowiwr01p.model.api.route.Route
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiResult
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType.*
import nowiwr01p.daily.doctor.encryption.client.EncryptionClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseApi(val apiClientSettings: ApiClientSettings): KoinComponent {

    protected val json by inject<Json>()
    protected val client by inject<HttpClient>()
    protected val encryptionHelper by inject<EncryptionClient>()

    /**
     * GET
     */
    protected suspend inline fun <reified T> getHttp(
        route: Route,
        parameters: List<ApiParameter> = listOf(),
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = GET,
            route = route,
            parameters = parameters,
            headers = headers
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> getHttp(
        route: Route,
        parameters: List<ApiParameter> = listOf(),
        handleError: (E) -> Unit,
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, E>(
            type = GET,
            route = route,
            parameters = parameters,
            handleError = handleError,
            headers = headers
        )
    }

    /**
     * POST
     */
    protected suspend inline fun <reified T> postHttp(
        route: Route,
        requestBody: Any? = null,
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = POST,
            route = route,
            requestBody = requestBody,
            headers = headers
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> postHttp(
        route: Route,
        requestBody: Any? = null,
        handleError: (E) -> Unit,
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, E>(
            type = POST,
            route = route,
            requestBody = requestBody,
            handleError = handleError,
            headers = headers
        )
    }

    /**
     * DELETE
     */
    protected suspend inline fun <reified T> deleteHttp(
        route: Route,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf(),
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = DELETE,
            route = route,
            requestBody = requestBody,
            parameters = parameters,
            headers = headers
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> deleteHttp(
        route: Route,
        body: Any? = null,
        handleError: (E) -> Unit,
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return baseHttpRequest<T, E>(
            type = DELETE,
            route = route,
            requestBody = body,
            handleError = handleError,
            headers = headers
        )
    }

    /**
     * BASE API REQUEST
     */
    protected suspend inline fun <reified T, reified E: ExpectedError> baseHttpRequest(
        type: HttpRequestType,
        route: Route,
        requestBody: Any? = null,
        crossinline headers: HeadersBuilder.() -> Unit = {},
        parameters: List<ApiParameter> = listOf(),
        handleError: (E) -> Unit = {}
    ): T {
//        val encodedBody = requestBody?.let { body ->
//            encryptionHelper.encodeFromClientToServer(body)
//        }
        val httpCallback: HttpRequestBuilder.() -> Unit = {
            url("${apiClientSettings.baseUrl}/${route.route}")
            headers {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json.toString())
                headers()
            }
            requestBody?.let { body ->
                setBody(requestBody)
            }
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
        }
        val httpResponse = when (type) {
            GET -> client.get(httpCallback)
            POST -> client.post(httpCallback)
            DELETE -> client.delete(httpCallback)
        }
        val publickey = httpResponse.headers["ascii"].orEmpty()
        encryptionHelper.setOtherSidePublicKey(publickey)
        return httpResponse.body<String>().let { body ->
            encryptionHelper.decodeOnClientFromServer<T>(body)
        }
    }

    /**
     * SAFE API CALL
     */
    protected inline fun <reified T, reified E> safeApiCall(route: Route, bodyString: String) = try {
        val response = json.decodeFromString<T>(bodyString)
        ApiResult.Success(response)
    } catch (error: Throwable) {
        val appUiError = try {
            json.decodeFromString<AppUiError>(bodyString)
        } catch (notAppUiError: Throwable) {
            null
        }
        when {
            appUiError != null -> {
                throw appUiError // UI error with only [message] field, must be shown
            }
            else -> if (E::class == NoErrorExpected::class) {
                val noErrorExpected = NoErrorExpected(
                    route = route.route,
                    errorMessage = bodyString
                )
                sendAnalyticUnexpectedError(noErrorExpected)
                throw noErrorExpected
            } else {
                try {
                    val decodedExpectedError = json.decodeFromString<E>(bodyString)
                    ApiResult.Error(decodedExpectedError)
                } catch (unexpectedError: Throwable) {
                    throw UnexpectedError(unexpectedError.message.orEmpty())
                }
            }
        }
    }

    /**
     * SEND ANALYTICS IF UNEXPECTED ERROR IN PROD
     */
    protected fun sendAnalyticUnexpectedError(error: NoErrorExpected) {
        // TODO: Send analytics and Telegram message
    }
}