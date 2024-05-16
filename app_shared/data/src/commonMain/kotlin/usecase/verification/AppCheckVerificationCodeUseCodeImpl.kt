package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.CheckVerificationCodeResponse
import repository.verification.AppVerificationRepository

class AppCheckVerificationCodeUseCodeImpl(
    private val repository: AppVerificationRepository
): AppCheckVerificationCodeUseCode {

    override suspend fun execute(input: CheckVerificationCodeRequest): CheckVerificationCodeResponse {
        return repository.checkVerificationCode(input)
    }
}