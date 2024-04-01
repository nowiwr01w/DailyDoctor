package domain.usecase.auth

import domain.model.user.UserData
import domain.repository.auth.ValidateAuthDataRepository
import domain.repository.auth.data.errors.AuthError
import domain.usecase.UseCase

class ValidateAuthDataUseCase(
    private val repository: ValidateAuthDataRepository
): UseCase<UserData, AuthError?> {

    override suspend fun execute(input: UserData): AuthError? {
        return repository.isAuthDataValid(input)
    }
}