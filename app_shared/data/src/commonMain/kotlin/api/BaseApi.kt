package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
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
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

abstract class BaseApi(override val di: DI): DIAware {

    protected val client by instance<HttpClient>()

    protected suspend inline fun <reified T> getHttp(
        route: String,
        parameters: List<ApiParameter> = listOf()
    ): T {
        return client.get {
            url("$BASE_URL/$route")
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
            generateHeaders()
        }.body<T>()
    }

    protected suspend inline fun <reified T> postHttp(
        route: String,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf()
    ): T {
        return client.post {
            url("$BASE_URL/$route")
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
            requestBody?.let {
                setBody(requestBody)
            }
            generateHeaders()
        }.body<T>()
    }

    protected fun HttpRequestBuilder.generateHeaders() = headers {
        contentType(ContentType.Application.Json)
        header(HttpHeaders.Accept, ContentType.Application.Json.toString())
    }

    protected companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }
}