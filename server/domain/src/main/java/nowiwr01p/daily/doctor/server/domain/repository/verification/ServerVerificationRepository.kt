package nowiwr01p.daily.doctor.server.domain.repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse

interface ServerVerificationRepository {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest): SendVerificationCodeResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): TokenResponse
}