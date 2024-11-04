package user.repository

import com.nowiwr01p.model.model.user.User

interface LocalUserRepository {
    suspend fun getUser(): User?
    suspend fun saveUser(user: User)
}