package api.verification

import api.Api
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface VerificationApi: Api {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest): TokenResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse
}