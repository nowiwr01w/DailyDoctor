package nowiwr01p.daily.doctor.server.data.di

import nowiwr01p.daily.doctor.server.data.repository.token.UserTokenConfigImpl
import nowiwr01p.daily.doctor.server.data.repository.token.ServerUserTokenRepositoryImpl
import nowiwr01p.daily.doctor.server.domain.repository.token.UserTokenConfig
import nowiwr01p.daily.doctor.server.domain.repository.token.ServerUserTokenRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerToken = DI.Module("ServerTokenModule") {
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