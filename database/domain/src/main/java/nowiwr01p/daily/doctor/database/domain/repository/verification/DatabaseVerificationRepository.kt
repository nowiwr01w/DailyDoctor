package nowiwr01p.daily.doctor.database.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface DatabaseVerificationRepository {
    suspend fun sendVerificationCode(token: String): TokenResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest)
    suspend fun deleteExpiredVerificationCodes()
}