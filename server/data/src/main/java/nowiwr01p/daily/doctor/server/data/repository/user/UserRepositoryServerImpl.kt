package nowiwr01p.daily.doctor.server.data.repository.user

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.user.UserRepositoryDatabase
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer

class UserRepositoryServerImpl(
    private val dispatchers: AppDispatchers,
    private val userRepositoryDatabase: UserRepositoryDatabase
): UserRepositoryServer {

    override suspend fun getUsers() = withContext(dispatchers.io) {
        userRepositoryDatabase.getUsers()
    }

    override suspend fun getUserById(id: String) = withContext(dispatchers.io) {
        userRepositoryDatabase.getUsersById(id)
    }
}