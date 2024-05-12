package nowiwr01p.daily.doctor.server.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.VerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.VerificationCodeResponse

interface ServerVerificationRepository {
    suspend fun sendVerificationCode(request: VerificationCodeRequest): VerificationCodeResponse
}