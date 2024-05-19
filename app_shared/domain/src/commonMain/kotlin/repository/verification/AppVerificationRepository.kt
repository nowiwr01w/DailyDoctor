package repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface AppVerificationRepository {
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse
}