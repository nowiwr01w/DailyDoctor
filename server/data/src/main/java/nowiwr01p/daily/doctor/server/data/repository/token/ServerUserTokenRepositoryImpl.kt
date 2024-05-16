package nowiwr01p.daily.doctor.server.data.repository.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.nowiwr01p.model.model.User
import nowiwr01p.daily.doctor.server.domain.repository.token.UserTokenConfig
import nowiwr01p.daily.doctor.server.domain.repository.token.ServerUserTokenRepository
import java.util.Date

class ServerUserTokenRepositoryImpl(
    private val config: UserTokenConfig
): ServerUserTokenRepository {

    override suspend fun generateUserToken(user: User) = JWT.create()
        .withSubject(user.id)
        .withIssuer(config.issuer)
        .withIssuedAt(Date())
        .withAudience(config.audience)
        .withClaim("username", user.email)
        .withExpiresAt(Date(System.currentTimeMillis() + config.expiredAfter))
        .sign(Algorithm.HMAC512(config.secretKey))
        .toString()
}