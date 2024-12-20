import app.AppViewModel
import auth.AuthViewModel
import com.nowiwr01p.model.coroutines.app_scope.AppScope
import helpers.snack_bar.SnackBarHelper
import home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import manager.brand_config.AppBrandConfigManager
import onboarding.OnboardingViewModel
import manager.onboarding.AppOnboardingManager
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import org.koin.dsl.module
import pin_code.PinCodeViewModel
import splash.SplashViewModel
import subscription.SubscriptionViewModel
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppValidateAuthDataUseCase
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCase
import user.usecase.GetLocalUserUseCase
import verification.VerificationViewModel

val moduleAppPresentationViewModels = module {
    /**
     * APP
     */
    single { (scope: CoroutineScope) ->
        AppViewModel(
            scope = scope,
            appBrandConfigManager = get<AppBrandConfigManager>()
        )
    }
    /**
     * SPLASH
     */
    factory { (scope: CoroutineScope) ->
        SplashViewModel(
            scope = scope,
            appScope = get<AppScope>(),
            getLocalUserUseCase = get<GetLocalUserUseCase>(),
            appBrandConfigManager = get<AppBrandConfigManager>(),
            appOnboardingManager = get<AppOnboardingManager>()
        )
    }
    /**
     * ONBOARDING
     */
    factory { (scope: CoroutineScope) ->
        OnboardingViewModel(
            scope = scope,
            appOnboardingManager = get<AppOnboardingManager>()
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
    /**
     * SUBSCRIPTION
     */
    factory { (scope: CoroutineScope) ->
        SubscriptionViewModel(scope)
    }
    /**
     * HOME
     */
    factory { (scope: CoroutineScope) ->
        HomeViewModel(scope)
    }
}
