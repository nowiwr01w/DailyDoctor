import Platform.DESKTOP
import Platform.WEB
import androidx.compose.runtime.Composable
import base.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import di.appModules
import navigation.MainNavigator.Child.OnboardingChild
import navigation.MainNavigator.Child.SplashChild
import navigation.MainNavigatorImpl
import navigation.auth.AuthNavigator
import navigation.onboarding.OnboardingNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ui.desktop.splash.SplashMainScreenDesktop
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.web.splash.SplashMainScreenWeb

@Composable
@Preview
fun App(mainComponent: MainNavigatorImpl) = withDI(appModules) {
    startLogger()
    AppTheme {
        AppContent(mainComponent)
    }
}

@Composable
private fun AppContent(mainComponent: MainNavigatorImpl) {
    Children(
        stack = mainComponent.stack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is SplashChild -> AppSplashScreen(navigator = child.component)
            is OnboardingChild -> AppOnboardingScreen(navigator = child.component)
        }
    }
}

/**
 * SPLASH SCREEN
 */
@Composable
private fun AppSplashScreen(navigator: OnboardingNavigator) = when (currentPlatform) {
    WEB -> SplashMainScreenWeb()
    DESKTOP -> SplashMainScreenDesktop()
    else -> SplashMainScreenMobile(navigator)
}

/**
 * ONBOARDING SCREEN
 */
@Composable
private fun AppOnboardingScreen(navigator: AuthNavigator) = when (currentPlatform) {
    WEB -> OnboardingMainScreenMobile(navigator)
    DESKTOP -> OnboardingMainScreenMobile(navigator)
    else -> OnboardingMainScreenMobile(navigator)
}