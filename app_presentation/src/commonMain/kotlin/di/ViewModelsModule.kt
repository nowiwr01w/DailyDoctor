package di

import ResendVerificationCodeTimerWork
import domain.usecase.auth.ValidateAuthDataUseCase
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance
import ui.common.auth.AuthViewModel
import ui.common.home.HomeViewModel
import ui.common.onboarding.OnboardingViewModel
import ui.common.pin_code.PinCodeViewModel
import ui.common.splash.SplashViewModel
import ui.common.verification.VerificationViewModel
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.verification.AppCheckVerificationCodeUseCase

val moduleViewModels = DI.Module("ViewModelsModule") {
    /**
     * SPLASH
     */
    bindFactory<CoroutineScope, SplashViewModel> { scope ->
        SplashViewModel(scope)
    }
    /**
     * ONBOARDING
     */
    bindFactory<CoroutineScope, OnboardingViewModel> { scope ->
        OnboardingViewModel(scope)
    }
    /**
     * AUTH
     */
    bindFactory<CoroutineScope, AuthViewModel> { scope ->
        AuthViewModel(
            scope = scope,
            signInUseCase = instance<AppSignInUseCase>(),
            signUpUseCase = instance<AppSignUpUseCase>(),
            authDataValidator = instance<ValidateAuthDataUseCase>(),
            snackBarHelper = instance<SnackBarHelper>()
        )
    }
    /**
     * VERIFICATION
     */
    bindFactory<CoroutineScope, VerificationViewModel> { scope ->
        VerificationViewModel(
            scope = scope,
            checkVerificationCodeUseCode = instance<AppCheckVerificationCodeUseCase>(),
            resendVerificationCodeTimerWork = instance<ResendVerificationCodeTimerWork>()
        )
    }
    /**
     * PIN CODE
     */
    bindFactory<CoroutineScope, PinCodeViewModel> { scope ->
        PinCodeViewModel(scope)
    }
    /**
     * HOME
     */
    bindFactory<CoroutineScope, HomeViewModel> { scope ->
        HomeViewModel(scope)
    }
}