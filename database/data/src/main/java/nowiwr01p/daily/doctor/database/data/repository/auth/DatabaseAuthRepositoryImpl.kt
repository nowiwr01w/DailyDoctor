package nowiwr01p.daily.doctor.database.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.model.user.User
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage

class DatabaseAuthRepositoryImpl(
    private val userStorage: DatabaseUserStorage
): BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest): User {
        val user = userStorage.getUser(request.phone) ?: run {
            buildError("It seems this phone number is not in our system.")
        }
        return when {
            request.password == user.password -> user
            else -> buildError("Wrong phone number or password.")
        }
    }

    override suspend fun signUp(request: SignUpRequest) = when {
        userStorage.getUser(request.phone) != null -> {
            buildError("This phone number cannot be used for registration.")
        }
        else -> userStorage.createUser(request)
    }
}