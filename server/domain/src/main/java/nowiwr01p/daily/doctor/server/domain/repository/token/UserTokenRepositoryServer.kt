package nowiwr01p.daily.doctor.server.domain.repository.token

import com.nowiwr01p.model.model.User

interface UserTokenRepositoryServer {
    suspend fun generateUserToken(user: User): String
}