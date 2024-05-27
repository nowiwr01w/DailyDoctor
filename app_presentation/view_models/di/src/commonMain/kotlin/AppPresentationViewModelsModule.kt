import auth.AuthViewModel
import helpers.snack_bar.SnackBarHelper
import kotlinx.coroutines.CoroutineScope
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import onboarding.OnboardingViewModel
import onboarding.data.OnboardingItem
import org.koin.dsl.module
import pin_code.PinCodeViewModel
import splash.SplashViewModel
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppValidateAuthDataUseCase
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCase
import verification.VerificationViewModel

val moduleAppPresentationViewModels = module {
    /**
     * SPLASH
     */
    factory { (scope: CoroutineScope) ->
        SplashViewModel(scope)
    }
    /**
     * ONBOARDING
     */
    factory { (scope: CoroutineScope, onboardingItems: List<OnboardingItem>) ->
        OnboardingViewModel(
            scope = scope,
            onboardingItems = onboardingItems
        )
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
    factory { (scope: CoroutineScope, pinCodeMode: PinCodeMode) ->
        PinCodeViewModel(
            scope = scope,
            pinCodeMode = pinCodeMode,
            snackBarHelper = get<SnackBarHelper>(),
            checkPinCodeUseCase = get<AppCheckPinCodeUseCase>(),
            createPinCodeUseCase = get<AppCreatePinCodeUseCase>(),
            changePinCodeUseCase = get<AppChangePinCodeUseCase>(),
            deletePinCodeUseCase = get<AppDeletePinCodeUseCase>()
        )
    }
}