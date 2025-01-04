package usecase

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import manager.brand_config.AppBrandConfigManager
import manager.onboarding.AppOnboardingManager
import manager.subscription.AppSubscriptionManager
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.brand_config.AppBrandConfigRepository
import repository.language.AppLanguageRepository
import repository.onboarding.AppOnboardingRepository
import repository.pin.AppPinRepository
import repository.subscription.AppSubscriptionRepository
import repository.verification.AppVerificationRepository
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignInUseCaseImpl
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppSignUpUseCaseImpl
import usecase.brand_config.AppGetBrandConfigUseCase
import usecase.brand_config.AppGetBrandConfigUseCaseImpl
import usecase.language.GetAppLanguagesUseCase
import usecase.language.GetAppLanguagesUseCaseImpl
import usecase.onboarding.AppGetOnboardingDataUseCase
import usecase.onboarding.AppGetOnboardingDataUseCaseImpl
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppChangePinCodeUseCaseImpl
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCaseImpl
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCaseImpl
import usecase.pin.AppDeletePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCaseImpl
import usecase.subscription.AppGetSubscriptionPlansUseCase
import usecase.subscription.AppGetSubscriptionPlansUseCaseImpl
import usecase.verification.AppCheckVerificationCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCaseImpl
import usecase.verification.AppSendVerificationCodeUseCase
import usecase.verification.AppSendVerificationCodeUseCaseImpl

internal val moduleAppSharedUseCase = module {
    /**
     * APP DATA
     */
    factory<InitAppDataUseCase> {
        InitAppDataUseCaseImpl(
            appScope = get<AppScope>(),
            appBrandConfigManager = get<AppBrandConfigManager>(),
            appOnboardingManager = get<AppOnboardingManager>(),
            appSubscriptionManager = get<AppSubscriptionManager>()
        )
    }
    /**
     * BRAND CONFIG
     */
    factory<AppGetBrandConfigUseCase> {
        AppGetBrandConfigUseCaseImpl(repository = get<AppBrandConfigRepository>())
    }
    /**
     * ONBOARDING
     */
    factory<AppGetOnboardingDataUseCase> {
        AppGetOnboardingDataUseCaseImpl(repository = get<AppOnboardingRepository>())
    }
    /**
     * AUTH
     */
    factory<AppSignInUseCase> {
        AppSignInUseCaseImpl(repository = get<AppAuthRepository>())
    }
    factory<AppSignUpUseCase> {
        AppSignUpUseCaseImpl(repository = get<AppAuthRepository>())
    }
    /**
     * VERIFICATION
     */
    factory<AppSendVerificationCodeUseCase> {
        AppSendVerificationCodeUseCaseImpl(repository = get<AppVerificationRepository>())
    }
    factory<AppCheckVerificationCodeUseCase> {
        AppCheckVerificationCodeUseCaseImpl(repository = get<AppVerificationRepository>())
    }
    /**
     * PIN
     */
    factory<AppChangePinCodeUseCase> {
        AppChangePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppCreatePinCodeUseCase> {
        AppCreatePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppDeletePinCodeUseCase> {
        AppDeletePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppCheckPinCodeUseCase> {
        AppCheckPinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    /**
     * LANGUAGE
     */
    factory<GetAppLanguagesUseCase> {
        GetAppLanguagesUseCaseImpl(repository = get<AppLanguageRepository>())
    }
    /**
     * SUBSCRIPTION
     */
    factory<AppGetSubscriptionPlansUseCase> {
        AppGetSubscriptionPlansUseCaseImpl(repository = get<AppSubscriptionRepository>())
    }
}