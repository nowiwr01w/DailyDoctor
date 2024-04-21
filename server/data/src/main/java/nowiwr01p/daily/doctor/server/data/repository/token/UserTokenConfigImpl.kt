package nowiwr01p.daily.doctor.server.data.repository.token

import nowiwr01p.daily.doctor.server.domain.repository.token.UserTokenConfig

data class UserTokenConfigImpl(
    override val issuer: String,
    override val audience: String,
    override val expiredAfter: Long,
    override val secretKey: String
) : UserTokenConfig