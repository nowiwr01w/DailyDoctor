package nowiwr01p.daily.doctor.server.domain.repository

import com.nowiwr01p.model.model.User

interface UserRepositoryServer {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}