package di

import ResendVerificationCodeTimerWork
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import ui.common.auth.AuthViewModel
import ui.common.home.HomeViewModel
import ui.common.onboarding.OnboardingViewModel
import ui.common.pin_code.PinCodeViewModel
import ui.common.splash.SplashViewModel
import ui.common.verification.VerificationViewModel
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppValidateAuthDataUseCase
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCase

val moduleAppViewModels = module {
    /**
     * SPLASH
     */
    factory { (scope: CoroutineScope) ->
        SplashViewModel(scope)
    }
    /**
     * ONBOARDING
     */
    factory { (scope: CoroutineScope) ->
        OnboardingViewModel(scope)
    }
    /**
     * AUTH
     */
    factory { (scope: CoroutineScope) ->
        AuthViewModel(
            scope = scope,
            signInUseCase = get<AppSignInUseCase>(),
            signUpUseCase = get<AppSignUpUseCase>(),
            authDataValidator = get<AppValidateAuthDataUseCase>(),
            snackBarHelper = get<SnackBarHelper>()
        )
    }
    /**
     * VERIFICATION
     */
    factory { (scope: CoroutineScope) ->
        VerificationViewModel(
            scope = scope,
            checkVerificationCodeUseCode = get<AppCheckVerificationCodeUseCase>(),
            resendVerificationCodeTimerWork = get<ResendVerificationCodeTimerWork>()
        )
    }
    /**
     * PIN CODE
     */
    factory { (scope: CoroutineScope) ->
        PinCodeViewModel(
            scope = scope,
            checkPinCodeUseCase = get<AppCheckPinCodeUseCase>(),
            createPinCodeUseCase = get<AppCreatePinCodeUseCase>(),
            changePinCodeUseCase = get<AppChangePinCodeUseCase>(),
            deletePinCodeUseCase = get<AppDeletePinCodeUseCase>()
        )
    }
    /**
     * HOME
     */
    factory { (scope: CoroutineScope) ->
        HomeViewModel(scope)
    }
}