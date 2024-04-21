package nowiwr01p.daily.doctor.server.domain.repository.token

interface UserTokenConfig {
    val issuer: String
    val audience: String
    val expiredAfter: Long
    val secretKey: String
}