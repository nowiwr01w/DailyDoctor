package nowiwr01p.daily.doctor.database.repository.user

import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User

interface DatabaseAuthRepository {
    suspend fun signIn(request: SignInRequest): User
    suspend fun signUp(request: SignUpRequest): User
}