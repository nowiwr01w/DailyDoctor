package nowiwr01p.daily.doctor.database.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest

interface DatabaseVerificationRepository {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest)
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest)
    suspend fun deleteExpiredVerificationCodes()
}