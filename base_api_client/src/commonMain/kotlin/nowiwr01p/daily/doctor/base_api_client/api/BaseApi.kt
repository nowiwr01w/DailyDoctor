package nowiwr01p.daily.doctor.base_api_client.api

import com.nowiwr01p.model.api.errors.AppError
import com.nowiwr01p.model.api.errors.HttpClientError.ApiRequestError
import com.nowiwr01p.model.api.errors.HttpClientError.ParsingApiResponseError
import com.nowiwr01p.model.api.route.BrantConfigRoutes
import com.nowiwr01p.model.api.route.Route
import com.nowiwr01p.model.extensions.runCatchingApp
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
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import logMessage
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType.DELETE
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType.GET
import nowiwr01p.daily.doctor.base_api_client.api.base.HttpRequestType.POST
import nowiwr01p.daily.doctor.encryption.client.EncryptionClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseApi<Error: AppError>(
    val apiClientSettings: ApiClientSettings
): KoinComponent {
    protected val json by inject<Json>()
    protected val client by inject<HttpClient>()
    protected val encryptionHelper by inject<EncryptionClient>()

    /**
     * GET
     */
    protected suspend inline fun <reified T, reified E: Error> getHttp(
        route: Route,
        error: (message: String) -> E,
        parameters: List<ApiParameter> = listOf(),
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return makeHttpRequest<T, E>(
            type = GET,
            route = route,
            error = error,
            parameters = parameters,
            headers = headers
        )
    }

    /**
     * POST
     */
    protected suspend inline fun <reified T, reified E: Error> postHttp(
        route: Route,
        error: (message: String) -> E,
        requestBody: Any? = null,
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return makeHttpRequest<T, E>(
            type = POST,
            route = route,
            error = error,
            requestBody = requestBody,
            headers = headers
        )
    }

    /**
     * DELETE
     */
    protected suspend inline fun <reified T, reified E: Error> deleteHttp(
        route: Route,
        error: (message: String) -> E,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf(),
        crossinline headers: HeadersBuilder.() -> Unit = {}
    ): T {
        return makeHttpRequest<T, E>(
            type = DELETE,
            route = route,
            error = error,
            requestBody = requestBody,
            parameters = parameters,
            headers = headers
        )
    }

    /**
     * BASE API REQUEST
     */
    protected suspend inline fun <reified T, reified E: Error> makeHttpRequest(
        route: Route,
        type: HttpRequestType,
        error: (message: String) -> E,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf(),
        crossinline headers: HeadersBuilder.() -> Unit = {},
    ): T {
        val encodedBody = runCatchingApp {
            requestBody?.let { body ->
                encryptionHelper.encodeFromClientToServer(body)
            }
        }.onSuccess {
            logMessage("makeHttpRequest, onSuccess = $it")
        }.onFailure {
            logMessage("makeHttpRequest, fail = ${it.message}")
        }.getOrNull()
        val httpCallback: HttpRequestBuilder.() -> Unit = {
            url("${apiClientSettings.baseUrl}/${route.route}")
            headers {
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json.toString())
                headers()
            }
            encodedBody?.let { body ->
                setBody(body)
            }
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
        }
        return makeApiCall(
            route = route,
            type = type,
            httpCallback = httpCallback,
            error = error
        )
    }

    /**
     * BUILD API RESULT
     */
    protected suspend inline fun <reified T, reified E: Error> makeApiCall(
        route: Route,
        type: HttpRequestType,
        error: (message: String) -> E,
        httpCallback: HttpRequestBuilder.() -> Unit
    ): T {
        return runCatching {
            executeHttpCallback<T>(
                route = route,
                type = type,
                httpCallback = httpCallback
            )
        }.mapCatching { parsedResponseModel ->
            parsedResponseModel
        }.getOrElse { safeApiCallError ->
            safeApiCallError.message.orEmpty().let { errorMessage ->
                logMessage("BaseApi, makeApiCall error = $errorMessage")
                throw error(errorMessage)
            }
        }
    }

    /**
     * EXECUTE API CALL AND PARSE RESPONSE MODEL
     */
    protected suspend inline fun <reified T> executeHttpCallback(
        route: Route,
        type: HttpRequestType,
        httpCallback: HttpRequestBuilder.() -> Unit
    ): T {
        return runCatching {
            when (type) {
                GET -> client.get(httpCallback)
                POST -> client.post(httpCallback)
                DELETE -> client.delete(httpCallback)
            }
        }.onFailure { error ->
            logMessage("executeHttpCallback onFailure error = ${error.message}")
            throw ApiRequestError(route, error)
        }.mapCatching { response ->
            handleSecretKeyFromBackend(route, response)
            val stringResponse = response.body<String>()
            parseModelFromStringResponse<T>(
                route = route,
                responseBodyString = stringResponse
            )
        }.getOrElse { error ->
            logMessage("executeHttpCallback getOrElse error = ${error.message}")
            throw error
        }
    }

    protected suspend inline fun <reified T> parseModelFromStringResponse(
        route: Route,
        responseBodyString: String
    ): T {
        return runCatching {
            encryptionHelper.decodeOnClientFromServer<T>(responseBodyString)
        }.mapCatching { model ->
            model
        }.getOrElse { error ->
            logMessage("parseModelFromStringResponse error = ${error.message}")
            throw ParsingApiResponseError(
                route = route,
                error = error,
                responseBodyString = responseBodyString
            )
        }
    }

    /**
     * HANDLE SECRET KEY FROM BACKEND
     */
    protected suspend fun handleSecretKeyFromBackend(route: Route, response: HttpResponse) {
        val isInitAppConfigRoute = route is BrantConfigRoutes.GetBrandConfigRoute
        val publicKey = response.headers["ascii"].orEmpty()
        val isHeaderWithKeyNotEmpty = publicKey.isNotEmpty()
        if (isInitAppConfigRoute && isHeaderWithKeyNotEmpty) {
            encryptionHelper.setOtherSidePublicKey(publicKey)
        }
    }
}