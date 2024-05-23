package usecase.auth

import model.errors.auth.AuthError
import model.user.UserData
import repository.auth.AppValidateAuthDataRepository

class AppValidateAuthDataUseCaseImpl(
    private val repository: AppValidateAuthDataRepository
): AppValidateAuthDataUseCase {

    override suspend fun execute(input: UserData): AuthError? {
        return repository.isAuthDataValid(input)
    }
}