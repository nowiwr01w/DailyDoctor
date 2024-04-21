package usecase.user

import com.nowiwr01p.model.model.User
import repository.user.AppUserRepository

class LoadAppUserByIdUseCaseImpl(
    private val repository: AppUserRepository
): LoadAppUserByIdUseCase {

    override suspend fun execute(input: Int): User {
        return repository.getUserById(input)
    }
}