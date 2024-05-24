package api

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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseApi: KoinComponent {

    protected val json by inject<Json>()
    protected val client by inject<HttpClient>()

    protected suspend inline fun <reified T> getHttp(
        route: String,
        parameters: List<ApiParameter> = listOf()
    ): T {
        val value = client.get {
            url("$BASE_URL/$route")
            parameters.forEach { param ->
                if (param.data != null) {
                    parameter(param.name, param.data)
                }
            }
            generateHeaders()
        }
        return json.decodeFromString(value.body<String>())
    }

    protected suspend inline fun <reified T> postHttp(
        route: String,
        requestBody: Any? = null,
        parameters: List<ApiParameter> = listOf()
    ): T {
        val value = client.post {
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
        }
        return json.decodeFromString(value.body<String>())
    }

    protected suspend inline fun <reified T> deleteHttp(route: String): T {
        return client.delete {
            url("$BASE_URL/$route")
            generateHeaders()
        }.body<T>() // TODO: Read from String
    }

    protected fun HttpRequestBuilder.generateHeaders() = headers {
        contentType(ContentType.Application.Json)
        header(HttpHeaders.Accept, ContentType.Application.Json.toString())
    }

    protected companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }
}