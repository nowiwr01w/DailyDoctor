package di

import domain.usecase.auth.ValidateAuthDataUseCase
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance
import ui.common.auth.AuthViewModel
import ui.common.home.HomeViewModel
import ui.common.onboarding.OnboardingViewModel
import ui.common.splash.SplashViewModel
import ui.common.verification.VerificationViewModel
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase

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
            authDataValidator = instance<ValidateAuthDataUseCase>()
        )
    }
    bindFactory<CoroutineScope, VerificationViewModel> { scope ->
        VerificationViewModel(scope)
    }
    /**
     * HOME
     */
    bindFactory<CoroutineScope, HomeViewModel> { scope ->
        HomeViewModel(scope)
    }
}