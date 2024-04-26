package repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.model.User

interface AppAuthRepository {
    suspend fun signUp(request: SignUpRequest): SignUpResponse
    suspend fun signIn(request: SignInRequest): User
}