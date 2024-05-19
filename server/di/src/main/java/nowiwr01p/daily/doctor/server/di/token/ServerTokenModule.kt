package nowiwr01p.daily.doctor.server.di.token

import nowiwr01p.daily.doctor.server.token.config.UserTokenConfig
import nowiwr01p.daily.doctor.server.token.config.UserTokenConfigImpl
import nowiwr01p.daily.doctor.server.token.repository.ServerUserTokenRepository
import nowiwr01p.daily.doctor.server.token.repository.ServerUserTokenRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleServerToken = DI.Module("ServerTokenModule") {
    /**
     * TOKEN CONFIG
     */
    bindProvider<UserTokenConfig> {
        UserTokenConfigImpl(
            issuer = "https://dailydoctor.ge/",
            audience = "https://dailydoctor.ge/api",
            expiredAfter = 2 * 60 * 1000L,
            secretKey = "v5qM9ll8QRdV81WpFcgzci1UNcY2vKm6c+yYNKMg6Zw+GjLevrMGtGBRNq7897npI7LBsu9T43F+E/Jmg26vRw=="
        )
    }
    /**
     * USER TOKEN REPOSITORY
     */
    bindProvider<ServerUserTokenRepository> {
        ServerUserTokenRepositoryImpl(config = instance<UserTokenConfig>())
    }
}