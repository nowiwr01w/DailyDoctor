package repository.auth

import api.auth.AuthApi
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext

class AppAuthRepositoryImpl(
    private val api: AuthApi,
    private val dispatchers: AppDispatchers
): AppAuthRepository {

    override suspend fun signUp(request: SignUpRequest) = withContext(dispatchers.io) {
        api.signUp(request)
    }

    override suspend fun signIn(request: SignInRequest) = withContext(dispatchers.io) {
        api.signIn(request)
    }
}