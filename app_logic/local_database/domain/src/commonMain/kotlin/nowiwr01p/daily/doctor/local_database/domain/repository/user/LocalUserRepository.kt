package nowiwr01p.daily.doctor.local_database.domain.repository.user

import com.nowiwr01p.model.model.user.User

interface LocalUserRepository {
    suspend fun getUser(): User?
    suspend fun saveUser(user: User)
}