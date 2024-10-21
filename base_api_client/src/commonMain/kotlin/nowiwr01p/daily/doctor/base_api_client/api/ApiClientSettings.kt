package nowiwr01p.daily.doctor.base_api_client.api

import platform.Platform
import platform.currentPlatform

sealed class ApiClientSettings(
    val baseUrl: String
) {
    data object TelegramApiClientSettings: ApiClientSettings(
        baseUrl = "https://gatewayapi.telegram.org/"
    )
    data object AppApiClientSettings: ApiClientSettings(
        baseUrl = when (currentPlatform) { // TODO: Move to platform module
            Platform.ANDROID -> "http://10.0.2.2:8080"
            else -> "http://localhost:8080"
        }
    )
}