package nowiwr01p.daily.doctor.server.domain.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface ServerAuthRepository {
    suspend fun signIn(request: SignInRequest): TokenResponse
    suspend fun signUp(request: SignUpRequest): TokenResponse
}