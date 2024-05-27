package nowiwr01p.daily.doctor.database.domain.storage.user

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.model.User

interface DatabaseUserStorage {
    fun createUser(request: SignUpRequest): User
    fun getUser(email: String): User?
    fun setUserVerified(email: String): User?
    fun setUserPinCodeToken(email: String, token: String): User?
}