package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import repository.verification.AppVerificationRepository

class AppCheckVerificationCodeUseCaseImpl(
    private val repository: AppVerificationRepository
): AppCheckVerificationCodeUseCase {

    override suspend fun execute(input: CheckVerificationCodeRequest) {
        repository.checkVerificationCode(input) // TODO: Save AuthToken
    }
}