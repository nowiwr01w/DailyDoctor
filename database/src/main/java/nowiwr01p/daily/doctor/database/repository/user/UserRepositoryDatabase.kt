package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.model.User

interface UserRepositoryDatabase {
    suspend fun getUsers(): List<User>
    suspend fun getUsersById(id: Int): User
}