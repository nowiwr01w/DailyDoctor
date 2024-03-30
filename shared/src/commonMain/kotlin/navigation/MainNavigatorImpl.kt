package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.MainNavigator.Child
import navigation.MainNavigatorImpl.AppNavigationConfig.Onboarding
import navigation.MainNavigatorImpl.AppNavigationConfig.Splash
import navigation.auth.AuthNavigatorImpl
import navigation.onboarding.OnboardingNavigatorImpl
import navigation.splash.SplashNavigatorImpl

class MainNavigatorImpl(
    private val context: ComponentContext
) : MainNavigator, ComponentContext by context {
    
    private val navigation = StackNavigation<AppNavigationConfig>()
    
    override val splashNavigator = buildSplashNavigator(context)
    override val onboardingNavigator = buildOnboardingNavigator(context)
    override val authNavigator = buildAuthNavigator(context)
    
    override val stack: Value<ChildStack<*, Child>>
        get() = childStack(
            source = navigation,
            serializer = AppNavigationConfig.serializer(),
            initialConfiguration = Splash,
            handleBackButton = true,
            childFactory = ::child
        )
    
    private fun child(config: AppNavigationConfig, context: ComponentContext) = when (config) {
        is Splash -> Child.SplashChild(buildOnboardingNavigator(context))    
        is Onboarding -> Child.OnboardingChild(buildAuthNavigator(context))
//        is Auth -> Child.AuthChild(buildAuthNavigator(context))
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun buildSplashNavigator(context: ComponentContext) = SplashNavigatorImpl(
        context = context,
        navigateToSpashCallback = { navigation.pushNew(Splash) }
    )
    
    @OptIn(ExperimentalDecomposeApi::class)
    private fun buildOnboardingNavigator(context: ComponentContext) = OnboardingNavigatorImpl(
        context = context,
        navigateToOnboardingCallback = { navigation.pushNew(Onboarding) }
    )
    
    @OptIn(ExperimentalDecomposeApi::class)
    private fun buildAuthNavigator(context: ComponentContext) = AuthNavigatorImpl(
        context = context,
        navigateToAuthCallback = {
//            navigation.pushNew(Auth)
        }
    )
    
    @Serializable
    private sealed interface AppNavigationConfig {
        @Serializable
        data object Splash: AppNavigationConfig
        @Serializable
        data object Onboarding: AppNavigationConfig
//        @Serializable
//        data object Auth: AppNavigationConfig
    }
}