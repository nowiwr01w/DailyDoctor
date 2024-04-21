package api.auth

import api.Api
import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User

interface AuthApi: Api {
    suspend fun signUp(request: SignUpRequest): User
    suspend fun signIn(request: SignInRequest): User
}