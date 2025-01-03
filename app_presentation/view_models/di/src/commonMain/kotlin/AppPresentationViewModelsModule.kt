import app.AppViewModel
import auth.AuthViewModel
import com.nowiwr01p.model.coroutines.app_scope.AppScope
import helpers.snack_bar.SnackBarHelper
import home.HomeViewModel
import manager.brand_config.AppBrandConfigManager
import manager.language.AppLanguageManager
import manager.onboarding.AppOnboardingManager
import nowiwr01p.daily.doctor.app_presentation.dialogs.select_language.SelectLanguageViewModel
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import com.nowiwr01p.model.resources.component_with_resources.screens.verification.VerificationScreenResources
import onboarding.OnboardingViewModel
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
import usecase.subscription.AppGetSubscriptionPlansUseCase
import usecase.verification.AppCheckVerificationCodeUseCase
import user.usecase.GetLocalUserUseCase
import verification.VerificationViewModel

val moduleAppPresentationViewModels = module {
    /**
     * APP
     */
    single {
        AppViewModel(
            appBrandConfigManager = get<AppBrandConfigManager>(),
            appLanguageManager = get<AppLanguageManager>(),
            getSubscriptionPlansUseCase = get<AppGetSubscriptionPlansUseCase>()
        )
    }
    /**
     * SPLASH
     */
    factory {
        SplashViewModel(
            appScope = get<AppScope>(),
            getLocalUserUseCase = get<GetLocalUserUseCase>(),
            appBrandConfigManager = get<AppBrandConfigManager>(),
            appOnboardingManager = get<AppOnboardingManager>()
        )
    }
    /**
     * ONBOARDING
     */
    factory {
        OnboardingViewModel(
            appOnboardingManager = get<AppOnboardingManager>()
        )
    }
    /**
     * AUTH
     */
    factory {
        AuthViewModel(
            signInUseCase = get<AppSignInUseCase>(),
            signUpUseCase = get<AppSignUpUseCase>(),
            authDataValidator = get<AppValidateAuthDataUseCase>(),
            snackBarHelper = get<SnackBarHelper>()
        )
    }
    /**
     * VERIFICATION
     */
    factory { (phone: String, verificationTokenFromAuth: String, screenResources: VerificationScreenResources) ->
        VerificationViewModel(
            phone = phone,
            verificationTokenFromAuth = verificationTokenFromAuth,
            screenResources = screenResources,
            checkVerificationCodeUseCode = get<AppCheckVerificationCodeUseCase>(),
            resendVerificationCodeTimerWork = get<ResendVerificationCodeTimerWork>()
        )
    }
    /**
     * PIN CODE
     */
    factory { (pinCodeMode: PinCodeMode) ->
        PinCodeViewModel(
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
    factory {
        SubscriptionViewModel()
    }
    /**
     * HOME
     */
    factory {
        HomeViewModel()
    }
    /**
     * CHANGE LANGUAGE DIALOG
     */
    factory {
        SelectLanguageViewModel(appLanguageManager = get<AppLanguageManager>())
    }
}
