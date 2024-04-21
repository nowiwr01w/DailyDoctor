package repository.user

import api.user.UserApi
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext

class AppUserRepositoryImpl(
    private val api: UserApi,
    private val dispatchers: AppDispatchers
): AppUserRepository {

    override suspend fun getUsers() = withContext(dispatchers.io) {
        api.getUsers()
    }

    override suspend fun getUserById(id: Int) = withContext(dispatchers.io) {
        api.getUserById(id)
    }
}