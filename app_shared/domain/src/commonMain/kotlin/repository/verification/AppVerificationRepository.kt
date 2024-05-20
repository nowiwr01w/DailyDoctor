package repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface AppVerificationRepository {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest): TokenResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse
}