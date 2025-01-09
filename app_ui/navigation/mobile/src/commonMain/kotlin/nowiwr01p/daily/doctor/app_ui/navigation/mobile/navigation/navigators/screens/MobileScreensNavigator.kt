package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.navigators.screens.ScreensNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.child.MobileScreensChild
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.home.HomeNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.subscription.SubscriptionNavigator

interface MobileScreensNavigator: ScreensNavigator {
    /**
     * CONFIG
     */
    val screensChildStack: Value<ChildStack<MobileScreensConfig, MobileScreensChild>>
    /**
     * NAVIGATORS
     */
    val splashNavigator: SplashNavigator
    val onboardingNavigator: OnboardingNavigator
    val authNavigator: AuthNavigator
    val pinCodeNavigator: PinCodeNavigator
    val subscriptionNavigator: SubscriptionNavigator
    val homeNavigator: HomeNavigator
}
