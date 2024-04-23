package navigation.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import navigation.MainNavigator
import navigation.MainNavigatorImpl
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.auth.AuthNavigator
import navigation.auth.AuthNavigatorImpl
import navigation.onboarding.OnboardingNavigator
import navigation.onboarding.OnboardingNavigatorImpl
import navigation.pin_code.PinCodeNavigator
import navigation.pin_code.PinCodeNavigatorImpl
import navigation.splash.SplashNavigator
import navigation.splash.SplashNavigatorImpl
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

typealias AppStackNavigation = StackNavigation<AppNavigationConfig>

val moduleNavigation = DI.Module("NavigationModule") {
    /**
     * SPLASH
     */
    bindFactory<AppStackNavigation, SplashNavigator> { navigation ->
        SplashNavigatorImpl(navigation)
    }
    /**
     * ONBOARDING
     */
    bindFactory<AppStackNavigation, OnboardingNavigator> { navigation ->
        OnboardingNavigatorImpl(navigation)
    }
    /**
     * AUTH
     */
    bindFactory<AppStackNavigation, AuthNavigator> { navigation ->
        AuthNavigatorImpl(navigation)
    }
    /**
     * PIN CODE
     */
    bindFactory<AppStackNavigation, PinCodeNavigator> { navigation ->
        PinCodeNavigatorImpl(navigation)
    }
    /**
     * MAIN
     */
    bindFactory<ComponentContext, MainNavigator> { context ->
        val navigation = StackNavigation<AppNavigationConfig>()
        MainNavigatorImpl(
            appContext = context,
            navigation = navigation,
            splashNavigator = instance<AppStackNavigation, SplashNavigator>(arg = navigation),
            onboardingNavigator = instance<AppStackNavigation, OnboardingNavigator>(arg = navigation),
            authNavigator = instance<AppStackNavigation, AuthNavigator>(arg = navigation),
            pinCodeNavigator = instance<AppStackNavigation, PinCodeNavigator>(arg = navigation)
        )
    }
}