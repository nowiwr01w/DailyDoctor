package manager

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import manager.brand_config.AppBrandConfigManager
import manager.brand_config.AppBrandConfigManagerImpl
import manager.language.AppLanguageManager
import manager.language.AppLanguageManagerImpl
import manager.onboarding.AppOnboardingManager
import manager.onboarding.AppOnboardingManagerImpl
import manager.subscription.AppSubscriptionManager
import manager.subscription.AppSubscriptionManagerImpl
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsGetLanguageUseCase
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsSetLanguageUseCase
import org.koin.dsl.module
import usecase.brand_config.AppGetBrandConfigUseCase
import usecase.language.GetAppLanguagesUseCase
import usecase.onboarding.AppGetOnboardingDataUseCase
import usecase.subscription.AppGetSubscriptionPlansUseCase

internal val moduleAppSharedManager = module {
    /**
     * BRAND CONFIG
     */
    single<AppBrandConfigManager> {
        AppBrandConfigManagerImpl(appGetBrandConfigUseCase = get<AppGetBrandConfigUseCase>())
    }
    /**
     * ONBOARDING
     */
    single<AppOnboardingManager> {
        AppOnboardingManagerImpl(
            appScope = get<AppScope>(),
            appGetOnboardingDataUseCase = get<AppGetOnboardingDataUseCase>()
        )
    }
    /**
     * APP LANGUAGE
     */
    single<AppLanguageManager> {
        AppLanguageManagerImpl(
            getAppLanguagesUseCase = get<GetAppLanguagesUseCase>(),
            appPrefsGetLanguageUseCase = get<AppPrefsGetLanguageUseCase>(),
            appPrefsSetLanguageUseCase = get<AppPrefsSetLanguageUseCase>()
        )
    }
    /**
     * SUBSCRIPTION
     */
    single<AppSubscriptionManager> {
        AppSubscriptionManagerImpl(getSubscriptionPlansUseCase = get<AppGetSubscriptionPlansUseCase>())
    }
}