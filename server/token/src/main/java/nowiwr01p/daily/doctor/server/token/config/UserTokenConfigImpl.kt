package nowiwr01p.daily.doctor.server.token.config

data class UserTokenConfigImpl(
    override val issuer: String,
    override val audience: String,
    override val expiredAfter: Long,
    override val secretKey: String
) : UserTokenConfig