package nowiwr01p.daily.doctor.server.di.token

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import nowiwr01p.daily.doctor.server.token.common.repository.ServerCommonTokenRepository
import nowiwr01p.daily.doctor.server.token.common.repository.ServerCommonTokenRepositoryImpl
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCaseImpl
import nowiwr01p.daily.doctor.server.token.user.config.UserTokenConfig
import nowiwr01p.daily.doctor.server.token.user.config.UserTokenConfigImpl
import nowiwr01p.daily.doctor.server.token.user.repository.ServerUserTokenRepository
import nowiwr01p.daily.doctor.server.token.user.repository.ServerUserTokenRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleServerToken = DI.Module("ServerTokenModule") {
    /**
     * USER TOKEN
     */
    bindProvider<UserTokenConfig> {
        UserTokenConfigImpl(
            issuer = "https://dailydoctor.ge/",
            audience = "https://dailydoctor.ge/api",
            expiredAfter = 2 * 60 * 1000L,
            secretKey = "v5qM9ll8QRdV81WpFcgzci1UNcY2vKm6c+yYNKMg6Zw+GjLevrMGtGBRNq7897npI7LBsu9T43F+E/Jmg26vRw=="
        )
    }
    bindProvider<ServerUserTokenRepository> {
        ServerUserTokenRepositoryImpl(config = instance<UserTokenConfig>())
    }
    /**
     * COMMON TOKEN
     */
    bindProvider<ServerCommonTokenRepository> {
        ServerCommonTokenRepositoryImpl(dispatchers = instance<AppDispatchers>())
    }
    bindProvider<ServerGenerateCommonTokenUseCase> {
        ServerGenerateCommonTokenUseCaseImpl(repository = instance<ServerCommonTokenRepository>())
    }
}