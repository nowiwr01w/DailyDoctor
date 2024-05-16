package repository.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse

interface AppVerificationRepository {
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): CheckVerificationCodeResponse
}