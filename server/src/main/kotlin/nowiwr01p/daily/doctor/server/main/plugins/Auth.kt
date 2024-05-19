package nowiwr01p.daily.doctor.server.main.plugins

import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.kodein.di.DI
import org.kodein.di.instance
import nowiwr01p.daily.doctor.server.token.user.config.UserTokenConfig

fun Application.configureAuthentication(di: DI) = install(Authentication) {
    val config by di.instance<UserTokenConfig>()
    jwt {
        realm = "DailyDoctor Main API"
        verifier(
            issuer = config.issuer,
            audience = config.audience,
            algorithm = Algorithm.HMAC512(config.secretKey)
        )
        validate { credential ->
            val isValid = with(credential.payload) {
                val hasIssuer = issuer.contains(config.issuer)
                val hasAudience = audience.contains(config.audience)
                val hasUserName = !getClaim("username").asString().isNullOrEmpty()
                hasUserName && hasIssuer && hasAudience
            }
            if (isValid) JWTPrincipal(credential.payload) else null
        }
        challenge { _, _ ->
            call.respond("Invalid token")
        }
    }
}