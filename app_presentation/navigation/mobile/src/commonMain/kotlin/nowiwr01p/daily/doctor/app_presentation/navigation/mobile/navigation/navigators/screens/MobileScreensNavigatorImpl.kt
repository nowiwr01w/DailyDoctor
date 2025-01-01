package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import navigation.navigators.screen_results.ScreenResultHandler
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.home.HomeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.subscription.SubscriptionNavigator
import view_model.BaseViewModelComponent

class MobileScreensNavigatorImpl(
    appNavigationContext: ComponentContext,
    private val navigation: StackNavigation<MobileScreensConfig>,
    private val screenResultHandler: ScreenResultHandler,
    override val splashNavigator: SplashNavigator,
    override val onboardingNavigator: OnboardingNavigator,
    override val authNavigator: AuthNavigator,
    override val pinCodeNavigator: PinCodeNavigator,
    override val subscriptionNavigator: SubscriptionNavigator,
    override val homeNavigator: HomeNavigator
): MobileScreensNavigator, ComponentContext by appNavigationContext {
    /**
     * CONFIG
     */
    override val screensChildStack = childStack(
        key = "ScreensChildStack",
        source = navigation,
        serializer = MobileScreensConfig.serializer(),
        initialConfiguration = MobileScreensConfig.Splash,
        handleBackButton = true,
        childFactory = { config, childContext ->
            config.child.apply {
                resultHandler = screenResultHandler
                baseComponent = BaseViewModelComponent(childContext)
            }
        }
    )

    /**
     * BACK NAVIGATION
     */
    override fun navigateBack(onComplete: (isSuccess: Boolean) -> Unit) {
        navigation.pop(onComplete = onComplete)
    }
}
