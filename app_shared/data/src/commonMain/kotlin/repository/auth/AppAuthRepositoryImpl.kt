package repository.auth

import api.auth.AuthApi
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.repository.BaseRepository
import kotlinx.coroutines.withContext

class AppAuthRepositoryImpl(
    private val api: AuthApi
): BaseRepository(), AppAuthRepository {

    override suspend fun signUp(request: SignUpRequest) = io {
        api.signUp(request)
    }

    override suspend fun signIn(request: SignInRequest) = io {
        api.signIn(request)
    }
}