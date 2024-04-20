package domain.repository.auth

import domain.model.user.UserData
import domain.repository.auth.data.errors.AuthError

interface ValidateAuthDataRepository {
    suspend fun isAuthDataValid(userData: UserData): AuthError?
}