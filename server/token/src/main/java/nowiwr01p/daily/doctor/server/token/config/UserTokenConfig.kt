package nowiwr01p.daily.doctor.server.token.config

interface UserTokenConfig {
    val issuer: String
    val audience: String
    val expiredAfter: Long
    val secretKey: String
}