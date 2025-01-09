package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.verification.AppVerificationRepository

class AppCheckVerificationCodeUseCaseImpl(
    private val repository: AppVerificationRepository
): AppCheckVerificationCodeUseCase {

    override suspend fun execute(input: CheckVerificationCodeRequest): TokenResponse {
        return repository.checkVerificationCode(input)
    }
}