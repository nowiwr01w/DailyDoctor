package nowiwr01p.daily.doctor.server.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface ServerVerificationRepository {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest)
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse
    suspend fun deleteExpiredVerificationCodes()
}