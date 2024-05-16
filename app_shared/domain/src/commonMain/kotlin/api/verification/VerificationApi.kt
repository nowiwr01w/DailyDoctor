package api.verification

import api.Api
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import com.nowiwr01p.model.api.response.verification.SendVerificationCodeResponse

interface VerificationApi: Api {
    suspend fun sendVerificationCode(request: SendVerificationCodeRequest): SendVerificationCodeResponse
    suspend fun checkVerificationCode(request: CheckVerificationCodeRequest): CheckVerificationCodeResponse
}