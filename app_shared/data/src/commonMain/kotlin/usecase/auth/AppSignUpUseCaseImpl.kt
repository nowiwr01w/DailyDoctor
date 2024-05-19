package usecase.auth

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import repository.auth.AppAuthRepository

class AppSignUpUseCaseImpl(
    private val repository: AppAuthRepository
): AppSignUpUseCase {

    override suspend fun execute(input: SignUpRequest): TokenResponse {
        return repository.signUp(input)
    }
}