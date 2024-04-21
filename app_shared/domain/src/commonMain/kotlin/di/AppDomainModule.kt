package di

import api.getKtorEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindInstance
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleDomainApp = DI.Module("AppDomainModule") {
    /**
     * KTOR CLIENT
     */
    bindInstance {
        Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }
    bindSingleton {
        HttpClient(getKtorEngine()) {
            install(ContentNegotiation) {
                json(instance())
            }
            defaultRequest {
//                url("http://10.0.2.2:8080/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}