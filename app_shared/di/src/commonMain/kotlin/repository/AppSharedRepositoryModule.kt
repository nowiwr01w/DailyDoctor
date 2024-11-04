package repository

import api.auth.AuthApi
import api.brand_config.BrandConfigApi
import api.pin.PinApi
import api.verification.VerificationApi
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.brand_config.AppBrandConfigRepository
import repository.brand_config.AppBrandConfigRepositoryImpl
import repository.pin.AppPinRepository
import repository.pin.AppPinRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl
import user.repository.LocalUserRepository

internal val moduleAppSharedRepository = module {
    /**
     * BRAND CONFIG
     */
    factory<AppBrandConfigRepository> {
        AppBrandConfigRepositoryImpl(api = get<BrandConfigApi>())
    }
    /**
     * AUTH
     */
    factory<AppAuthRepository> {
        AppAuthRepositoryImpl(
            api = get<AuthApi>(),
            repository = get<LocalUserRepository>()
        )
    }
    /**
     * VERIFICATION
     */
    factory<AppVerificationRepository> {
        AppVerificationRepositoryImpl(api = get<VerificationApi>())
    }
    /**
     * PIN
     */
    factory<AppPinRepository> {
        AppPinRepositoryImpl(api = get<PinApi>())
    }
}