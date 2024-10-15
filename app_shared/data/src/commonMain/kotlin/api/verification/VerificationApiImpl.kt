package api.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.VerificationRoutes.CheckVerificationCodeRoute
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.*
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class VerificationApiImpl: BaseApi(AppApiClientSettings), VerificationApi {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest): TokenResponse {
        return postHttp<TokenResponse>(
            route = SendVerificationCodeRoute,
            requestBody = request
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse {
        return postHttp<TokenResponse>(
            route = CheckVerificationCodeRoute,
            requestBody = request
        )
    }
}