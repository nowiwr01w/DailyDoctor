package nowiwr01p.daily.doctor.app_presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.*
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.*
import nowiwr01p.daily.doctor.app_presentation.navigation.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.home.HomeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.subscription.SubscriptionNavigator

typealias AppStackNavigation = StackNavigation<MainNavigatorImpl.AppNavigationConfig>

class MainNavigatorImpl(
    appContext: ComponentContext,
    navigation: AppStackNavigation,
    override val splashNavigator: SplashNavigator,
    override val onboardingNavigator: OnboardingNavigator,
    override val authNavigator: AuthNavigator,
    override val pinCodeNavigator: PinCodeNavigator,
    override val subscriptionNavigator: SubscriptionNavigator,
    override val homeNavigator: HomeNavigator
): MainNavigator, ComponentContext by appContext {

    override val stack = childStack(
        source = navigation,
        serializer = AppNavigationConfig.serializer(),
        initialConfiguration = Splash,
        handleBackButton = true,
        childFactory = { config, childContext ->
            config.child.also { child ->
                child.updateChildContext(childContext)
            }
        }
    )

    @Serializable
    sealed class AppNavigationConfig(val child: Child) {
        @Serializable
        data object Splash: AppNavigationConfig(
            child = SplashChild
        )
        @Serializable
        data class Onboarding(val onboardingItem: OnboardingItemModel): AppNavigationConfig(
            child = OnboardingChild(onboardingItem)
        )
        @Serializable
        data object Auth: AppNavigationConfig(
            child = AuthChild
        )
        @Serializable
        data class Verification(val phone: String, val verificationToken: String): AppNavigationConfig(
            child = VerificationChild(phone, verificationToken)
        )
        @Serializable
        data class PinCode(val pinCodeMode: PinCodeMode): AppNavigationConfig(
            child = PinCodeChild(pinCodeMode)
        )
        @Serializable
        data object Subscription: AppNavigationConfig(
            child = SubscriptionChild
        )
        @Serializable
        data object Home: AppNavigationConfig(
            child = HomeChild
        )
    }

    private fun Child.updateChildContext(childContext: ComponentContext) = when (this) {
        is SplashChild -> splashNavigator
        is OnboardingChild -> onboardingNavigator
        is AuthChild, is VerificationChild -> authNavigator
        is PinCodeChild -> pinCodeNavigator
        is SubscriptionChild -> subscriptionNavigator
        is HomeChild -> homeNavigator
    }.also { navigator ->
        navigator.updateChildContext(childContext)
    }
}