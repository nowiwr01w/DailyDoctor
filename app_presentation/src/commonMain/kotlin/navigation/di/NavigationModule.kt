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
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

typealias AppStackNavigation = StackNavigation<AppNavigationConfig>

val moduleNavigation = module {
    /**
     * SPLASH
     */
    single<SplashNavigator> { (navigation: AppStackNavigation) ->
        SplashNavigatorImpl(navigation)
    }
    /**
     * ONBOARDING
     */
    single<OnboardingNavigator> { (navigation: AppStackNavigation) ->
        OnboardingNavigatorImpl(navigation)
    }
    /**
     * AUTH
     */
    single<AuthNavigator> { (navigation: AppStackNavigation) ->
        AuthNavigatorImpl(navigation)
    }
    /**
     * PIN CODE
     */
    single<PinCodeNavigator> { (navigation: AppStackNavigation) ->
        PinCodeNavigatorImpl(navigation)
    }
    /**
     * MAIN
     */
    single<MainNavigator> { (context: ComponentContext) ->
        val navigation = StackNavigation<AppNavigationConfig>()
        MainNavigatorImpl(
            appContext = context,
            navigation = navigation,
            splashNavigator = get<SplashNavigator> { parametersOf(navigation) },
            onboardingNavigator = get<OnboardingNavigator> { parametersOf(navigation) },
            authNavigator = get<AuthNavigator> { parametersOf(navigation) },
            pinCodeNavigator = get<PinCodeNavigator> { parametersOf(navigation) }
        )
    }
}