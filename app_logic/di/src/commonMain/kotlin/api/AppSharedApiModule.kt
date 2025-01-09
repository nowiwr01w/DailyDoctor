package api

import api.auth.AuthApi
import api.auth.AuthApiImpl
import api.brand_config.BrandConfigApi
import api.brand_config.BrandConfigApiImpl
import api.onboarding.OnboardingApi
import api.onboarding.OnboardingApiImpl
import api.pin.PinApi
import api.pin.PinApiImpl
import api.subscription.SubscriptionApi
import api.subscription.SubscriptionApiImpl
import api.verification.VerificationApi
import api.verification.VerificationApiImpl
import org.koin.dsl.module

internal val moduleAppSharedApi = module {
    /**
     * BRAND CONFIG
     */
    single<BrandConfigApi> {
        BrandConfigApiImpl()
    }
    /**
     * ONBOARDING
     */
    single<OnboardingApi> {
        OnboardingApiImpl()
    }
    /**
     * AUTH
     */
    single<AuthApi> {
        AuthApiImpl()
    }
    /**
     * VERIFICATION
     */
    single<VerificationApi> {
        VerificationApiImpl()
    }
    /**
     * PIN
     */
    single<PinApi> {
        PinApiImpl()
    }
    /**
     * SUBSCRIPTION
     */
    single<SubscriptionApi> {
        SubscriptionApiImpl()
    }
}