package manager

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import manager.brand_config.AppBrandConfigManager
import manager.brand_config.AppBrandConfigManagerImpl
import manager.language.AppLanguageManager
import manager.language.AppLanguageManagerImpl
import manager.onboarding.AppOnboardingManager
import manager.onboarding.AppOnboardingManagerImpl
import org.koin.dsl.module
import usecase.brand_config.AppGetBrandConfigUseCase
import usecase.language.GetAppLanguagesUseCase
import usecase.onboarding.AppGetOnboardingDataUseCase

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
        AppLanguageManagerImpl(getAppLanguagesUseCase = get<GetAppLanguagesUseCase>())
    }
}