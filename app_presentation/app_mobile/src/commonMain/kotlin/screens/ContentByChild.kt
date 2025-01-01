package screens

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.*
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.auth.AuthMainScreenResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.splash.SplashMainScreenResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification.VerificationMainScreenResources
import screens.auth.AuthMainScreenMobile
import screens.home.HomeMainScreen
import screens.onboarding.OnboardingMainScreenMobile
import screens.pin_code.PinCodeMainScreenMobile
import screens.splash.SplashMainScreenMobile
import screens.subscription.SubscriptionMainScreen
import screens.verification.VerificationMainScreenMobile

@Composable
internal fun MobileScreensChild.getScreenContent(navigator: MobileNavigator) = when (this) {
    is SplashChild -> SplashMainScreenResources {
        SplashMainScreenMobile(navigator = navigator, resources = it)
    }
    is OnboardingChild -> {
        OnboardingMainScreenMobile(navigator)
    }
    is AuthChild -> AuthMainScreenResources {
        AuthMainScreenMobile(navigator = navigator, resources = it)
    }
    is VerificationChild -> VerificationMainScreenResources {
        VerificationMainScreenMobile(navigator = navigator, resources = it)
    }
    is PinCodeChild -> {
        PinCodeMainScreenMobile(navigator)
    }
    is SubscriptionChild -> {
        SubscriptionMainScreen(navigator)
    }
    is HomeChild -> {
        HomeMainScreen(navigator)
    }
}
