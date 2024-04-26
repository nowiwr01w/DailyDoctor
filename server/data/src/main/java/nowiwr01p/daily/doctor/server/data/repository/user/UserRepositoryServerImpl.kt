package nowiwr01p.daily.doctor.server.data.repository.user

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import nowiwr01p.daily.doctor.database.repository.user.DatabaseAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer

class UserRepositoryServerImpl(
    private val dispatchers: AppDispatchers,
    private val repository: DatabaseAuthRepository
): UserRepositoryServer {

    override suspend fun signIn(request: SignInRequest) = withContext(dispatchers.io) {
        repository.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequest) = withContext(dispatchers.io) {
        repository.signUp(request)
    }
}