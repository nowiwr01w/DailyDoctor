package nowiwr01p.daily.doctor.database.domain.repository.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.model.user.User

interface DatabaseAuthRepository {
    suspend fun signIn(request: SignInRequest): User
    suspend fun signUp(request: SignUpRequest): User
}