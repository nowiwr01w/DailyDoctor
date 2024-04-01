package domain.usecase.auth

import domain.model.user.UserData
import domain.repository.auth.AuthRepository
import domain.repository.auth.data.user.User
import domain.usecase.UseCase

class SignInUseCase(
    private val repository: AuthRepository
): UseCase<UserData, User> {

    override suspend fun execute(input: UserData): User {
        return repository.signIn(input)
    }
}