package api.user

import api.Api
import com.nowiwr01p.model.model.User

interface UserApi: Api {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}