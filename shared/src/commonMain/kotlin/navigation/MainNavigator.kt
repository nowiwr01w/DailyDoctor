package navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.auth.AuthNavigator
import navigation.onboarding.OnboardingNavigator
import navigation.splash.SplashNavigator

interface MainNavigator {

    val stack: Value<ChildStack<*, Child>>
    
    val splashNavigator: SplashNavigator
    val onboardingNavigator: OnboardingNavigator
    val authNavigator: AuthNavigator
    
    sealed class Child {
        data class SplashChild(val component: OnboardingNavigator): Child()
        data class OnboardingChild(val component: AuthNavigator): Child()
    }
}