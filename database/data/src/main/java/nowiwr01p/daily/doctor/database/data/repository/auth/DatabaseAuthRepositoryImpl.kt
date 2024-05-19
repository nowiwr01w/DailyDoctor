package nowiwr01p.daily.doctor.database.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage

class DatabaseAuthRepositoryImpl(
    private val userStorage: DatabaseUserStorage
): BaseRepository(), DatabaseAuthRepository {

    override suspend fun signIn(request: SignInRequest) = userStorage.getUser(request.email) ?: run {
        buildError("Wrong email or password.")
    }

    override suspend fun signUp(request: SignUpRequest) = when {
        userStorage.getUser(request.email) != null -> {
            buildError("This email cannot be used for registration.")
        }
        else -> userStorage.createUser(request)
    }
}