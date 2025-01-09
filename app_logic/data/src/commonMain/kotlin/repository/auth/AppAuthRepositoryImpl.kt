package repository.auth

import api.auth.AuthApi
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.model.user.User
import nowiwr01p.daily.doctor.local_database.domain.repository.user.LocalUserRepository
import repository.AppBaseRepository

class AppAuthRepositoryImpl(
    private val api: AuthApi,
    private val repository: LocalUserRepository
): AppBaseRepository(), AppAuthRepository {

    override suspend fun signUp(request: SignUpRequest) = io {
        api.signUp(request)
    }

    override suspend fun signIn(request: SignInRequest) = io {
        api.signIn(request).also {
            repository.saveUser(User()) // TODO: Auth - get the User?
        }
    }
}