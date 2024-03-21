import Platform.DESKTOP
import Platform.WEB
import androidx.compose.runtime.Composable
import base.theme.AppTheme
import di.appModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ui.desktop.splash.SplashMainScreenDesktop
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.web.splash.SplashMainScreenWeb

@Composable
@Preview
fun App() = withDI(appModules) {
    startLogger()
    AppTheme {
        AppContent()
    }
}

@Composable
private fun AppContent() = when (currentPlatform) {
    WEB -> SplashMainScreenWeb()
    DESKTOP -> SplashMainScreenDesktop()
    else -> OnboardingMainScreenMobile()
}