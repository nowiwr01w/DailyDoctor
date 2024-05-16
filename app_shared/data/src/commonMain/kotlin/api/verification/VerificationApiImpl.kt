package api.verification

import api.BaseApi
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.AuthTokenResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse
import com.nowiwr01p.model.api.route.VerificationRoutes.CheckVerificationCodeRoute
import com.nowiwr01p.model.api.route.VerificationRoutes.SendVerificationCodeRoute
import org.kodein.di.DI

class VerificationApiImpl(kodein: DI): BaseApi(kodein), VerificationApi {

    override suspend fun sendVerificationCode(
        request: SendVerificationCodeRequest
    ): SendVerificationCodeResponse {
        return postHttp<SendVerificationCodeResponse>(
            route = SendVerificationCodeRoute.route,
            requestBody = request
        )
    }

    override suspend fun checkVerificationCode(
        request: CheckVerificationCodeRequest
    ): AuthTokenResponse {
        return postHttp<AuthTokenResponse>(
            route = CheckVerificationCodeRoute.route,
            requestBody = request
        )
    }
}