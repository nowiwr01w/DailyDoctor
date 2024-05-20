package usecase.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.verification.AppVerificationRepository

class AppSendVerificationCodeUseCaseImpl(
    private val repository: AppVerificationRepository
): AppSendVerificationCodeUseCase {

    override suspend fun execute(input: SendVerificationCodeRequest): TokenResponse {
        return repository.sendVerificationCode(input)
    }
}