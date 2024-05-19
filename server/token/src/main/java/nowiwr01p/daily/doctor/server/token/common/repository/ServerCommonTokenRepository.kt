package nowiwr01p.daily.doctor.server.token.common.repository

interface ServerCommonTokenRepository {
    suspend fun generateToken(): String
}