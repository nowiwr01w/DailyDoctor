package nowiwr01p.daily.doctor.server.data.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository

class ServerAuthRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val repository: DatabaseAuthRepository
): ServerAuthRepository {

    override suspend fun signIn(request: SignInRequest) = withContext(dispatchers.io) {
        repository.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequest) = withContext(dispatchers.io) {
        repository.signUp(request)
    }
}