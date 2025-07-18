package repository

import api.auth.AuthApi
import api.brand_config.BrandConfigApi
import api.onboarding.OnboardingApi
import api.pin.PinApi
import api.subscription.SubscriptionApi
import api.verification.VerificationApi
import nowiwr01p.daily.doctor.local_database.domain.repository.user.LocalUserRepository
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.brand_config.AppBrandConfigRepository
import repository.brand_config.AppBrandConfigRepositoryImpl
import repository.onboarding.AppOnboardingRepository
import repository.onboarding.AppOnboardingRepositoryImpl
import repository.pin.AppPinRepository
import repository.pin.AppPinRepositoryImpl
import repository.subscription.AppSubscriptionRepository
import repository.subscription.AppSubscriptionRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl

internal val moduleAppSharedRepository = module {
    /**
     * BRAND CONFIG
     */
    factory<AppBrandConfigRepository> {
        AppBrandConfigRepositoryImpl(api = get<BrandConfigApi>())
    }
    /**
     * ONBOARDING
     */
    factory<AppOnboardingRepository> {
        AppOnboardingRepositoryImpl(api = get<OnboardingApi>())
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
    /**
     * SUBSCRIPTION
     */
    factory<AppSubscriptionRepository> {
        AppSubscriptionRepositoryImpl(api = get<SubscriptionApi>())
    }
}