package nowiwr01p.daily.doctor.tg_sms.data.api

import io.ktor.http.HttpHeaders.Authorization
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.TelegramApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.tg_sms.domain.api.TgSmsApi
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.api.response.VerificationResponseWrap
import nowiwr01p.daily.doctor.tg_sms.domain.routes.TelegramRoutes.SendVerificationMessage

class TgSmsApiImpl(
    private val apiKey: String
): BaseApi(TelegramApiClientSettings), TgSmsApi {

    override suspend fun sendVerificationCode(request: VerificationRequest) = run {
        postHttp<VerificationResponseWrap>(
            route = SendVerificationMessage,
            requestBody = request,
            headers = { append(Authorization, "Bearer $apiKey") }
        )
    }
}