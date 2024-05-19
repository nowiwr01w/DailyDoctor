package nowiwr01p.daily.doctor.database.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.token.VerificationTokenResponse
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.storage.user.DatabaseUserStorage

class DatabaseAuthRepositoryImpl(
    private val userStorage: DatabaseUserStorage
): BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest): User {
        val existedUser = userStorage.getUser(request.email)
        return existedUser ?: buildError("Wrong email or password.")
    }

    override suspend fun signUp(request: SignUpRequest): TokenResponse {
        if (userStorage.getUser(request.email) != null) {
            buildError("This email cannot be used for registration.")
        }
        userStorage.createUser(request)
        return VerificationTokenResponse(token = "1234") // TODO: Generate token
    }
}