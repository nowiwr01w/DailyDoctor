package navigation

import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.auth.AuthNavigator
import navigation.onboarding.OnboardingNavigator
import navigation.pin_code.PinCodeNavigator
import navigation.splash.SplashNavigator
import ui.common.onboarding.data.OnboardingItem
import ui.common.pin_code.data.PinCodeMode

interface MainNavigator {

    val stack: Value<ChildStack<AppNavigationConfig, Child>>
    
    val splashNavigator: SplashNavigator
    val onboardingNavigator: OnboardingNavigator
    val authNavigator: AuthNavigator
    val pinCodeNavigator: PinCodeNavigator

    @Serializable
    sealed class Child(
        val animation: StackAnimator = slide()
    ) {
        data object SplashChild: Child()

        data class OnboardingChild(
            val onboardingItem: OnboardingItem
        ): Child()

        data object AuthChild: Child()

        data class VerificationChild(
            val email: String,
            val verificationToken: String
        ): Child()

        data class PinCodeChild(
            val pinCodeMode: PinCodeMode
        ): Child()
    }
}