package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import navigation.MainNavigator.Child
import navigation.MainNavigator.Child.AuthChild
import navigation.MainNavigator.Child.OnboardingChild
import navigation.MainNavigator.Child.SplashChild
import navigation.MainNavigatorImpl.AppNavigationConfig.Splash
import navigation.auth.AuthNavigator
import navigation.di.AppStackNavigation
import navigation.onboarding.OnboardingNavigator
import navigation.splash.SplashNavigator
import ui.common.onboarding.data.OnboardingItem

class MainNavigatorImpl(
    appContext: ComponentContext,
    navigation: AppStackNavigation,
    override val splashNavigator: SplashNavigator,
    override val onboardingNavigator: OnboardingNavigator,
    override val authNavigator: AuthNavigator,
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
        data object Splash: AppNavigationConfig(child = SplashChild)
        @Serializable
        data class Onboarding(val onboardingItem: OnboardingItem): AppNavigationConfig(
            child = OnboardingChild(onboardingItem)
        )
        @Serializable
        data object Auth: AppNavigationConfig(child = AuthChild)
    }

    private fun Child.updateChildContext(childContext: ComponentContext) = when (this) {
        is SplashChild -> splashNavigator
        is OnboardingChild -> onboardingNavigator
        is AuthChild -> authNavigator
    }.also { navigator ->
        navigator.updateChildContext(childContext)
    }
}