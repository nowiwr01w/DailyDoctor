package nowiwr01p.daily.doctor.server.domain.repository.token

import com.nowiwr01p.model.model.User

interface ServerUserTokenRepository {
    suspend fun generateUserToken(user: User): String
}