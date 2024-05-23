package nowiwr01p.daily.doctor.app_presentation.navigation.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import nowiwr01p.daily.doctor.app_presentation.navigation.AppStackNavigation
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.auth.AuthNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.OnboardingNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.PinCodeNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.splash.SplashNavigatorImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val moduleAppPresentationNavigation = module {
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
        val navigation = StackNavigation<MainNavigatorImpl.AppNavigationConfig>()
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