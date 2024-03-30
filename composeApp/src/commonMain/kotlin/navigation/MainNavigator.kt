package navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.auth.AuthNavigator
import navigation.onboarding.OnboardingNavigator
import navigation.splash.SplashNavigator
import ui.common.onboarding.data.OnboardingItem

interface MainNavigator {

    val stack: Value<ChildStack<*, Child>>
    
    val splashNavigator: SplashNavigator
    val onboardingNavigator: OnboardingNavigator
    val authNavigator: AuthNavigator
    
    sealed class Child {
        data object SplashChild: Child()
        data class OnboardingChild(val onboardingItem: OnboardingItem): Child()
    }
}