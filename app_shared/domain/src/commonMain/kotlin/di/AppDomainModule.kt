package di

import api.getKtorEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val moduleDomainShared = module {
    /**
     * KTOR CLIENT
     */
    factory {
        Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient(getKtorEngine()) {
            install(ContentNegotiation) {
                json(get<Json>())
            }
        }
    }
}