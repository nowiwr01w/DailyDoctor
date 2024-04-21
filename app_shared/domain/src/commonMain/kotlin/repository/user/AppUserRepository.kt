package repository.user

import com.nowiwr01p.model.model.User

interface AppUserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}