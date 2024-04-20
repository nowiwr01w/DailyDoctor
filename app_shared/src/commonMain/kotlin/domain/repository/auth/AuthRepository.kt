package domain.repository.auth

import domain.model.user.UserData
import domain.repository.auth.data.user.User

interface AuthRepository {
    suspend fun signIn(userData: UserData): User
    suspend fun signUp(userData: UserData): User
}