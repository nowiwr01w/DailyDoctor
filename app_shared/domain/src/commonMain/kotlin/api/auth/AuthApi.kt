package api.auth

import api.Api
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface AuthApi: Api {
    suspend fun signUp(request: SignUpRequest): TokenResponse
    suspend fun signIn(request: SignInRequest): TokenResponse
}