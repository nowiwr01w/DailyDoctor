package screens

import androidx.compose.runtime.Composable
import com.nowiwr01p.model.resources.component_with_resources.screens.auth.AuthMainScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.pin.PinMainScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.screen_info.SubscriptionMainScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.verification.VerificationMainScreenResources
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.HomeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SubscriptionChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.VerificationChild
import screens.auth.AuthMainScreenMobile
import screens.home.HomeMainScreen
import screens.onboarding.OnboardingMainScreenMobile
import screens.pin_code.PinCodeMainScreenMobile
import screens.splash.SplashMainScreenMobile
import screens.subscription.SubscriptionMainScreen
import screens.verification.VerificationMainScreenMobile

@Composable
internal fun MobileScreensChild.getScreenContent(navigator: MobileNavigator) = when (this) {
    is SplashChild -> {
        SplashMainScreenMobile(navigator)
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
    is PinCodeChild -> PinMainScreenResources {
        PinCodeMainScreenMobile(navigator = navigator, resources = it)
    }
    is SubscriptionChild -> SubscriptionMainScreenResources {
        SubscriptionMainScreen(navigator = navigator, resources = it)
    }
    is HomeChild -> {
        HomeMainScreen(navigator)
    }
}
