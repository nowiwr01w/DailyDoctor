package usecase.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.model.User
import repository.auth.AppAuthRepository

class AppSignInUseCaseImpl(
    private val repository: AppAuthRepository
): AppSignInUseCase {

    override suspend fun execute(input: SignInRequest): User {
        return repository.signIn(input)
    }
}