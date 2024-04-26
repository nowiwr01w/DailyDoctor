package nowiwr01p.daily.doctor.server.domain.repository.user

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.model.User

interface UserRepositoryServer {
    suspend fun signIn(request: SignInRequest): User
    suspend fun signUp(request: SignUpRequest): SignUpResponse
}