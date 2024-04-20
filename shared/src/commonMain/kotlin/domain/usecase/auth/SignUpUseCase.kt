package domain.usecase.auth

import domain.model.user.UserData
import domain.repository.auth.AuthRepository
import domain.repository.auth.data.user.User
import com.nowiwr01p.model.usecase.UseCase

class SignUpUseCase(
    private val repository: AuthRepository
): UseCase<UserData, User> {

    override suspend fun execute(input: UserData): User {
        return repository.signUp(input)
    }
}