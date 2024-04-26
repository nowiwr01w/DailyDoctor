package api.auth

import api.Api
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.model.User

interface AuthApi: Api {
    suspend fun signUp(request: SignUpRequest): SignUpResponse
    suspend fun signIn(request: SignInRequest): User
}