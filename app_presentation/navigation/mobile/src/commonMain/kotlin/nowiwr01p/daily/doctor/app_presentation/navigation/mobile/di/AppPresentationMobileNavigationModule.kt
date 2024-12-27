package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import navigation.config.config.DialogsNavigationConfig
import navigation.navigators.dialogs.DialogsNavigator
import navigation.navigators.dialogs.DialogsNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileBottomSheetConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets.MobileBottomSheetsNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets.MobileBottomSheetsNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.MobileScreensNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.MobileScreensNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.auth.AuthNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.auth.AuthNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.home.HomeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.home.HomeNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.onboarding.OnboardingNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.onboarding.OnboardingNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.pin_code.PinCodeNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.pin_code.PinCodeNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.splash.SplashNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.splash.SplashNavigatorImpl
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.subscription.SubscriptionNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.subscription.SubscriptionNavigatorImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

private typealias AppStackNavigation = StackNavigation<MobileScreensConfig>

val moduleAppPresentationMobileNavigation = module {
    /**
     * MAIN
     */
    single<MobileNavigator> { (context: ComponentContext) ->
        MobileNavigatorImpl(
            screensNavigator = get<MobileScreensNavigator> { parametersOf(context) },
            dialogsNavigator = get<DialogsNavigator> { parametersOf(context) },
            bottomSheetsNavigator = get<MobileBottomSheetsNavigator> { parametersOf(context) }
        )
    }
    /**
     * BOTTOM SHEETS
     */
    single<MobileBottomSheetsNavigator> { (context: ComponentContext) ->
        val navigation = SlotNavigation<MobileBottomSheetConfig>()
        MobileBottomSheetsNavigatorImpl(
            appNavigationContext = context,
            appScope = get<AppScope>(),
            dispatchers = get<AppDispatchers>(),
            navigation = navigation
        )
    }
    /**
     * DIALOGS
     */
    single<DialogsNavigator> { (context: ComponentContext) ->
        val navigation = SlotNavigation<DialogsNavigationConfig>()
        DialogsNavigatorImpl(
            appNavigationContext = context,
            appScope = get<AppScope>(),
            dispatchers = get<AppDispatchers>(),
            navigation = navigation
        )
    }
    /**
     * SCREENS
     */
    single<MobileScreensNavigator> { (context: ComponentContext) ->
        val navigation = StackNavigation<MobileScreensConfig>()
        MobileScreensNavigatorImpl(
            appNavigationContext = context,
            navigation = navigation,
            splashNavigator = get<SplashNavigator> { parametersOf(navigation) },
            onboardingNavigator = get<OnboardingNavigator> { parametersOf(navigation) },
            authNavigator = get<AuthNavigator> { parametersOf(navigation) },
            pinCodeNavigator = get<PinCodeNavigator> { parametersOf(navigation) },
            subscriptionNavigator = get<SubscriptionNavigator> { parametersOf(navigation) },
            homeNavigator = get<HomeNavigator> { parametersOf(navigation) }
        )
    }
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
     * SUBSCRIPTION
     */
    single<SubscriptionNavigator> { (navigation: AppStackNavigation) ->
        SubscriptionNavigatorImpl(navigation)
    }
    /**
     * HOME
     */
    single<HomeNavigator> { (navigation: AppStackNavigation) ->
        HomeNavigatorImpl(navigation)
    }
}
