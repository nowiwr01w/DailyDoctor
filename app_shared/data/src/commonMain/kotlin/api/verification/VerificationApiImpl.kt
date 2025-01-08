package api.verification

import com.nowiwr01p.model.api.errors.verification.VerificationApiError
import com.nowiwr01p.model.api.errors.verification.VerificationApiError.CheckVerificationCodeApiError
import com.nowiwr01p.model.api.errors.verification.VerificationApiError.SendVerificationCodeApiError
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.VerificationRoutes.CheckVerificationCodeRoute
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.*
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class VerificationApiImpl: BaseApi<VerificationApiError>(AppApiClientSettings), VerificationApi {
    /**
     * SEND
     */
    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest) = run {
        postHttp<TokenResponse, SendVerificationCodeApiError>(
            route = SendVerificationCodeRoute,
            requestBodyString = request.encodeDataToString(),
            error = { message -> SendVerificationCodeApiError(message) }
        )
    }

    /**
     * CHECK
     */
    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest) = run {
        postHttp<TokenResponse, CheckVerificationCodeApiError>(
            route = CheckVerificationCodeRoute,
            requestBodyString = request.encodeDataToString(),
            error = { message -> CheckVerificationCodeApiError(message) }
        )
    }
}