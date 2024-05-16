package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.AuthTokenResponse
import repository.verification.AppVerificationRepository

class AppCheckVerificationCodeUseCodeImpl(
    private val repository: AppVerificationRepository
): AppCheckVerificationCodeUseCode {

    override suspend fun execute(input: CheckVerificationCodeRequest) {
        repository.checkVerificationCode(input) // TODO: Save AuthToken
    }
}