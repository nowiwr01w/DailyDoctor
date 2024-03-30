
import Platform.DESKTOP
import Platform.WEB
import androidx.compose.runtime.Composable
import base.theme.AppTheme
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import di.appModules
import navigation.MainNavigator
import navigation.MainNavigator.Child.AuthChild
import navigation.MainNavigator.Child.OnboardingChild
import navigation.MainNavigator.Child.SplashChild
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.rememberFactory
import org.kodein.di.compose.withDI
import ui.common.onboarding.data.OnboardingItem
import ui.desktop.splash.SplashMainScreenDesktop
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.web.splash.SplashMainScreenWeb

@Composable
@Preview
fun App(context: ComponentContext) = withDI(appModules) {
    val mainNavigatorFactory by rememberFactory<ComponentContext, MainNavigator>()
    val mainNavigator = mainNavigatorFactory(context)
    startLogger()
    AppTheme {
        AppContent(mainNavigator)
    }
}

@Composable
private fun AppContent(navigator: MainNavigator) {
    Children(
        stack = navigator.stack,
        animation = stackAnimation { child -> child.instance.animation }
    ) {
        when (val child = it.instance) {
            is SplashChild -> {
                AppSplashScreen(navigator = navigator)
            }
            is OnboardingChild -> AppOnboardingScreen(
                navigator = navigator,
                onboardingItem = child.onboardingItem
            )
            is AuthChild -> {
                // TODO: AuthScreen
            }
        }
    }
}

/**
 * SPLASH SCREEN
 */
@Composable
private fun AppSplashScreen(navigator: MainNavigator) = when (currentPlatform) {
    WEB -> SplashMainScreenWeb()
    DESKTOP -> SplashMainScreenDesktop()
    else -> SplashMainScreenMobile(navigator)
}

/**
 * ONBOARDING SCREEN
 */
@Composable
private fun AppOnboardingScreen(
    navigator: MainNavigator,
    onboardingItem: OnboardingItem
) {
    val onboardingScreen: @Composable () -> Unit = {
        OnboardingMainScreenMobile(
            navigator = navigator,
            onboardingItem = onboardingItem
        )
    }
    when (currentPlatform) {
        WEB -> onboardingScreen()
        DESKTOP -> onboardingScreen()
        else -> onboardingScreen()
    }
}