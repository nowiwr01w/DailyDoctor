package screens

import androidx.compose.runtime.Composable
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.*
import screens.auth.AuthMainScreenMobile
import screens.home.HomeMainScreen
import screens.onboarding.OnboardingMainScreenMobile
import screens.pin_code.PinCodeMainScreenMobile
import screens.splash.SplashMainScreenMobile
import screens.subscription.SubscriptionMainScreen
import screens.verification.VerificationMainScreenMobile

@Composable
internal fun MobileScreensChild.getScreenContent(navigator: MobileNavigator) = when (this) {
    is SplashChild -> SplashMainScreenMobile(navigator)
    is OnboardingChild -> OnboardingMainScreenMobile(navigator)
    is AuthChild -> AuthMainScreenMobile(navigator)
    is VerificationChild -> VerificationMainScreenMobile(navigator)
    is PinCodeChild -> PinCodeMainScreenMobile(navigator)
    is SubscriptionChild -> SubscriptionMainScreen(navigator)
    is HomeChild -> HomeMainScreen(navigator)
}
