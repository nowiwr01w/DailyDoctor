package nowiwr01p.daily.doctor.tg_sms

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://gatewayapi.telegram.org/"
private const val TOKEN = "AAEFAAAAQKI_mDsJppSEQRr3kLOz9SatBxq48BgQLSHLRv"
private const val PHONE = "+391234567890"

val client = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        level = LogLevel.INFO
    }
}

suspend fun postRequestStatus(endpoint: String, jsonBody: Any): Map<String, Any>? {
    return try {
        val response = client.post(urlString = "$BASE_URL$endpoint") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $TOKEN")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
            setBody(jsonBody)
        }
        if (response.status == HttpStatusCode.OK) {
            val responseJson: Map<String, Any> = response.body()
            if (responseJson["ok"] == true) {
                responseJson["result"] as? Map<String, Any>
            } else {
                val errorMessage = responseJson["error"] ?: "Unknown error"
                println("Error: $errorMessage")
                null
            }
        } else {
            println("Failed to get request status: HTTP ${response.status.value}")
            null
        }
    } catch (e: Exception) {
        println("Request failed: ${e.message}")
        null
    }
}

fun main() = runBlocking {
    val result = postRequestStatus(
        endpoint = "sendVerificationMessage",
        jsonBody = mapOf(
            "phone_number" to PHONE,
            "code_length" to 6,
            "ttl" to 60,
            "payload" to "my_payload_here",
            "callback_url" to "https://my.webhook.here/auth"
        )
    )
    println("Result: $result")
}