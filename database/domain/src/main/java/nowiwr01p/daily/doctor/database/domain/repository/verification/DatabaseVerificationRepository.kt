package nowiwr01p.daily.doctor.database.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface DatabaseVerificationRepository {
    suspend fun sendVerificationCode(
        token: String,
        code: String
    ): TokenResponse
    suspend fun checkVerificationCode(
        token: String,
        request: CheckVerificationCodeRequest
    ): TokenResponse
    suspend fun deleteExpiredVerificationCodes()
}