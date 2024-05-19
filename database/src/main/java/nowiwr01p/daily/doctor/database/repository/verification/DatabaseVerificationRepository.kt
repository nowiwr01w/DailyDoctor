package nowiwr01p.daily.doctor.database.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse

interface DatabaseVerificationRepository {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest): SendVerificationCodeResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest)
    suspend fun deleteExpiredVerificationCodes()
}