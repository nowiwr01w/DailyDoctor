package api.verification

import api.BaseApi
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.VerificationRoutes.CheckVerificationCodeRoute
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute

class VerificationApiImpl: BaseApi(), VerificationApi {

    override suspend fun sendVerificationCode(request: SendVerificationCodeRequest): TokenResponse {
        return postHttp<TokenResponse>(
            route = SendVerificationCodeRoute.route,
            requestBody = request
        )
    }

    override suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse {
        return postHttp<TokenResponse>(
            route = CheckVerificationCodeRoute.route,
            requestBody = request
        )
    }
}