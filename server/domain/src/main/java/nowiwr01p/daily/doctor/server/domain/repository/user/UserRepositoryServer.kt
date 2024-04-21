package nowiwr01p.daily.doctor.server.domain.repository.user

import com.nowiwr01p.model.api.request.CreateUserRequest
import com.nowiwr01p.model.model.User

interface UserRepositoryServer {
    suspend fun createUser(user: CreateUserRequest): User
    suspend fun getUserById(id: String): User
}