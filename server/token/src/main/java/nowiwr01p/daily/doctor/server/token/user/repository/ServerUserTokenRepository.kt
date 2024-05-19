package nowiwr01p.daily.doctor.server.token.user.repository

import com.nowiwr01p.model.model.User

interface ServerUserTokenRepository {
    suspend fun generateUserToken(user: User): String
}