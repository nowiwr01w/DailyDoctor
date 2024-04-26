package usecase.auth

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import repository.auth.AppAuthRepository

class AppSignUpUseCaseImpl(
    private val repository: AppAuthRepository
): AppSignUpUseCase {

    override suspend fun execute(input: SignUpRequest): SignUpResponse {
        return repository.signUp(input)
    }
}