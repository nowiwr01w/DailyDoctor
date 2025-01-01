package nowiwr01p.daily.doctor.base_api_client.api

sealed class ApiClientSettings(
    val baseUrl: String
) {
    data object TelegramApiClientSettings: ApiClientSettings(
        baseUrl = "https://gatewayapi.telegram.org/"
    )
    data object AppApiClientSettings: ApiClientSettings(
        baseUrl = "http://127.0.0.1:8080"
    )
}