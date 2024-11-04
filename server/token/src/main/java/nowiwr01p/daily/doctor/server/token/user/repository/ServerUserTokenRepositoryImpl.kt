package nowiwr01p.daily.doctor.server.token.user.repository

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.nowiwr01p.model.model.user.User
import nowiwr01p.daily.doctor.server.token.user.config.UserTokenConfig
import java.util.Date

class ServerUserTokenRepositoryImpl(
    private val config: UserTokenConfig
): ServerUserTokenRepository {

    override suspend fun generateUserToken(user: User) = JWT.create()
        .withSubject(user.id)
        .withIssuer(config.issuer)
        .withIssuedAt(Date())
        .withAudience(config.audience)
        .withClaim("username", user.phone)
        .withExpiresAt(Date(System.currentTimeMillis() + config.expiredAfter))
        .sign(Algorithm.HMAC512(config.secretKey))
        .toString()
}