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
import org.koin.dsl.module

internal val moduleServerToken = module {
    /**
     * USER TOKEN
     */
    /**
     * USER TOKEN
     */
    factory<UserTokenConfig> {
        UserTokenConfigImpl(
            issuer = "https://dailydoctor.ge/",
            audience = "https://dailydoctor.ge/api",
            expiredAfter = 2 * 60 * 1000L,
            secretKey = "v5qM9ll8QRdV81WpFcgzci1UNcY2vKm6c+yYNKMg6Zw+GjLevrMGtGBRNq7897npI7LBsu9T43F+E/Jmg26vRw=="
        )
    }
    factory<ServerUserTokenRepository> {
        ServerUserTokenRepositoryImpl(config = get<UserTokenConfig>())
    }
    /**
     * COMMON TOKEN
     */
    /**
     * COMMON TOKEN
     */
    factory<ServerCommonTokenRepository> {
        ServerCommonTokenRepositoryImpl(dispatchers = get<AppDispatchers>())
    }
    factory<ServerGenerateCommonTokenUseCase> {
        ServerGenerateCommonTokenUseCaseImpl(repository = get<ServerCommonTokenRepository>())
    }
}