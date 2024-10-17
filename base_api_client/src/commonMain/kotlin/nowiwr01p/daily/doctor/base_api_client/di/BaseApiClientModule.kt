package nowiwr01p.daily.doctor.base_api_client.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import nowiwr01p.daily.doctor.base_api_client.api.engine.getKtorEngine
import org.koin.dsl.module

val moduleBaseApiClient = module {
    /**
     * JSON
     */
    factory {
        Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }
    /**
     * HTTP CLIENT
     */
    single {
        HttpClient(getKtorEngine()) {
            install(ContentNegotiation) {
                json(get<Json>())
            }
        }
    }
}