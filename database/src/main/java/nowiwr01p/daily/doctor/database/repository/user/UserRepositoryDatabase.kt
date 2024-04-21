package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.CreateUserRequest
import com.nowiwr01p.model.model.User

interface UserRepositoryDatabase {
    suspend fun createUser(request: CreateUserRequest): User
    suspend fun getUsersById(id: String): User
}