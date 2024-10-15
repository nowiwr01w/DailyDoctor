package nowiwr01p.daily.doctor.base_api_client.api

sealed class ApiClientSettings(val baseUrl: String) {
    data object AppApiClientSettings: ApiClientSettings(baseUrl = "http://10.0.2.2:8080")
    data object TelegramApiClientSettings: ApiClientSettings(baseUrl = "https://gatewayapi.telegram.org/")
}