package repository.auth

import model.errors.auth.AuthError
import model.user.UserData

interface AppValidateAuthDataRepository {
    suspend fun isAuthDataValid(userData: UserData): AuthError?
}