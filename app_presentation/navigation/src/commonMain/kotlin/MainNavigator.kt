package nowiwr01p.daily.doctor.app_presentation.navigation

import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.home.HomeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.subscription.SubscriptionNavigator

interface MainNavigator {

    val stack: Value<ChildStack<AppNavigationConfig, Child>>
    
    val splashNavigator: SplashNavigator
    val onboardingNavigator: OnboardingNavigator
    val authNavigator: AuthNavigator
    val pinCodeNavigator: PinCodeNavigator
    val subscriptionNavigator: SubscriptionNavigator
    val homeNavigator: HomeNavigator

    @Serializable
    sealed class Child(
        val animation: StackAnimator = slide()
    ) {
        @Serializable
        data object SplashChild: Child()

        @Serializable
        data class OnboardingChild(
            val onboardingItem: OnboardingItemModel
        ): Child()

        @Serializable
        data object AuthChild: Child()

        @Serializable
        data class VerificationChild(
            val email: String,
            val verificationToken: String
        ): Child()

        @Serializable
        data class PinCodeChild(
            val pinCodeMode: PinCodeMode
        ): Child()

        @Serializable
        data object SubscriptionChild: Child()

        @Serializable
        data object HomeChild: Child()
    }
}