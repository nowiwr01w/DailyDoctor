package nowiwr01p.daily.doctor.server.data.repository.user

import com.nowiwr01p.model.api.request.CreateUserRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.user.UserRepositoryDatabase
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer

class UserRepositoryServerImpl(
    private val dispatchers: AppDispatchers,
    private val userRepositoryDatabase: UserRepositoryDatabase
): UserRepositoryServer {

    override suspend fun createUser(request: CreateUserRequest) = withContext(dispatchers.io) {
        userRepositoryDatabase.createUser(request)
    }

    override suspend fun getUserById(id: String) = withContext(dispatchers.io) {
        userRepositoryDatabase.getUsersById(id)
    }
}