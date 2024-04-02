package data.repository.auth

import domain.coroutines.dispatchers.AppDispatchers
import domain.model.user.UserData
import domain.repository.auth.AuthRepository
import domain.repository.auth.data.user.User
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val dispatchers: AppDispatchers
): AuthRepository {

    /**
     * SIGN IN
     */
    override suspend fun signIn(userData: UserData) = withContext(dispatchers.io) {
        User(userData.email)
    }

    /**
     * SIGN UP
     */
    override suspend fun signUp(userData: UserData) = withContext(dispatchers.io) {
        User(userData.email)
    }
}