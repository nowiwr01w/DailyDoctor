package network

import api.getKtorEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val moduleAppSharedNetwork = module {
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
                val json = Json {
                    isLenient = true
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
                json(json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}