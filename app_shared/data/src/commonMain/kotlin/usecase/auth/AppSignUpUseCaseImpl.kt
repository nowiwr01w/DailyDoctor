package usecase.auth

import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import repository.auth.AppAuthRepository

class AppSignUpUseCaseImpl(
    private val repository: AppAuthRepository
): AppSignUpUseCase {

    override suspend fun execute(input: SignUpRequest): User {
        return repository.signUp(input)
    }
}