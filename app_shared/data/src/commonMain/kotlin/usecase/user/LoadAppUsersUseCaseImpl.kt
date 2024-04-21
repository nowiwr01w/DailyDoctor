package usecase.user

import com.nowiwr01p.model.model.User
import repository.user.AppUserRepository

class LoadAppUsersUseCaseImpl(
    private val repository: AppUserRepository
): LoadAppUsersUseCase {

    override suspend fun execute(input: Unit): List<User> {
        return repository.getUsers()
    }
}