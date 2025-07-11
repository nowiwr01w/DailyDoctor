package api.auth

import nowiwr01p.daily.doctor.base_api_client.api.Api
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse

interface AuthApi: Api {
    suspend fun signUp(request: SignUpRequest): TokenResponse
    suspend fun signIn(request: SignInRequest): TokenResponse
}