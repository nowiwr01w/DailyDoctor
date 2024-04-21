package repository.auth

import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User

interface AppAuthRepository {
    suspend fun signUp(request: SignUpRequest): User
    suspend fun signIn(request: SignInRequest): User
}