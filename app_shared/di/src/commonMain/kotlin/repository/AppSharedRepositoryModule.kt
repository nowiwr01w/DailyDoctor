package repository

import api.auth.AuthApi
import api.pin.PinApi
import api.verification.VerificationApi
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.brand.AppBrandRepository
import repository.brand.AppBrandRepositoryImpl
import repository.pin.AppPinRepository
import repository.pin.AppPinRepositoryImpl
import repository.theme.AppThemeRepository
import repository.theme.AppThemeRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl

internal val moduleAppSharedRepository = module {
    /**
     * THEME
     */
    single<AppThemeRepository> {
        AppThemeRepositoryImpl()
    }
    /**
     * BRAND
     */
    factory<AppBrandRepository> {
        AppBrandRepositoryImpl()
    }
    /**
     * AUTH
     */
    factory<AppAuthRepository> {
        AppAuthRepositoryImpl(
            api = get<AuthApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
    /**
     * VERIFICATION
     */
    factory<AppVerificationRepository> {
        AppVerificationRepositoryImpl(
            api = get<VerificationApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
    /**
     * PIN
     */
    factory<AppPinRepository> {
        AppPinRepositoryImpl(
            api = get<PinApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
}