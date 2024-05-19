package repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.model.User

interface AppAuthRepository {
    suspend fun signUp(request: SignUpRequest): TokenResponse
    suspend fun signIn(request: SignInRequest): User
}