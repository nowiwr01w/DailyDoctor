package repository

import api.auth.AuthApi
import api.pin.PinApi
import api.verification.VerificationApi
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.pin.AppPinRepository
import repository.pin.AppPinRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl
import user.repository.LocalUserRepository

internal val moduleAppSharedRepository = module {
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