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
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiResult
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseApi(
    protected val apiClientSettings: ApiClientSettings
): KoinComponent {

    protected val json by inject<Json>()
    protected val client by inject<HttpClient>()

    /**
     * GET
     */
    protected suspend inline fun <reified T> getHttp(
        route: Route,
        parameters: List<ApiParameter> = listOf()
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = GET,
            route = route,
            parameters = parameters
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> getHttp(
        route: Route,
        parameters: List<ApiParameter> = listOf(),
        handleError: (E) -> Unit
    ): T {
        return baseHttpRequest<T, E>(
            type = GET,
            route = route,
            parameters = parameters,
            handleError = handleError
        )
    }

    /**
     * POST
     */
    protected suspend inline fun <reified T> postHttp(
        route: Route,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf()
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = POST,
            route = route,
            requestBody = requestBody,
            parameters = parameters
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> postHttp(
        route: Route,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf(),
        handleError: (E) -> Unit
    ): T {
        return baseHttpRequest<T, E>(
            type = POST,
            route = route,
            parameters = parameters,
            requestBody = requestBody,
            handleError = handleError
        )
    }

    /**
     * DELETE
     */
    protected suspend inline fun <reified T> deleteHttp(
        route: Route,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf()
    ): T {
        return baseHttpRequest<T, NoErrorExpected>(
            type = DELETE,
            route = route,
            requestBody = requestBody,
            parameters = parameters
        )
    }

    protected suspend inline fun <reified T, reified E: ExpectedError> deleteHttp(
        route: Route,
        body: Any? = null,
        handleError: (E) -> Unit
    ): T {
        return baseHttpRequest<T, E>(
            type = DELETE,
            route = route,
            requestBody = body,
            handleError = handleError
        )
    }

    /**
     * BASE API REQUEST
     */
    protected suspend inline fun <reified T, reified E: ExpectedError> baseHttpRequest(
        type: HttpRequestType,
        route: Route,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf(),
        handleError: (E) -> Unit = {}
    ): T {
        val httpCallback: HttpRequestBuilder.() -> Unit = {
            url("${apiClientSettings.baseUrl}/${route.route}")
            headers {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json.toString())
            }
            requestBody?.let {
                setBody(requestBody)
            }
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
        }
        val stringResponse = when (type) {
            GET -> client.get(httpCallback)
            POST -> client.post(httpCallback)
            DELETE -> client.delete(httpCallback)
        }
        val apiResult = safeApiCall<T, E>(
            route = route,
            bodyString = stringResponse.body<String>()
        )
        return when (apiResult) {
            is ApiResult.Success -> apiResult.data
            is ApiResult.Error -> {
                handleError(apiResult.errorData)
                throw ExpectedError(apiResult.errorData.message)
            }
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