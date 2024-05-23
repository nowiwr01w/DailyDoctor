package usecase

import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.brand.AppBrandRepository
import repository.pin.AppPinRepository
import repository.theme.AppThemeRepository
import repository.verification.AppVerificationRepository
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignInUseCaseImpl
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppSignUpUseCaseImpl
import usecase.brand.AppGetBrandUseCase
import usecase.brand.AppGetBrandUseCaseImpl
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppChangePinCodeUseCaseImpl
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCaseImpl
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCaseImpl
import usecase.pin.AppDeletePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCaseImpl
import usecase.theme.AppGetThemeModeUseCase
import usecase.theme.AppGetThemeModeUseCaseImpl
import usecase.theme.SetAppThemeModeUseCase
import usecase.theme.SetAppThemeModeUseCaseImpl
import usecase.verification.AppCheckVerificationCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCaseImpl
import usecase.verification.AppSendVerificationCodeUseCase
import usecase.verification.AppSendVerificationCodeUseCaseImpl

internal val moduleAppSharedUseCase = module {
    /**
     * THEME
     */
    factory<AppGetThemeModeUseCase> {
        AppGetThemeModeUseCaseImpl(repository = get<AppThemeRepository>())
    }
    factory<SetAppThemeModeUseCase> {
        SetAppThemeModeUseCaseImpl(repository = get<AppThemeRepository>())
    }
    /**
     * BRAND
     */
    factory<AppGetBrandUseCase> {
        AppGetBrandUseCaseImpl(repository = get<AppBrandRepository>())
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
}