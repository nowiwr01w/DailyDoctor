
import Platform.DESKTOP
import Platform.WEB
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import base.theme.AppTheme
import base.theme.CustomTheme.colors
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import di.appModules
import navigation.MainNavigator
import navigation.MainNavigator.Child.AuthChild
import navigation.MainNavigator.Child.OnboardingChild
import navigation.MainNavigator.Child.SplashChild
import navigation.MainNavigator.Child.VerificationChild
import org.kodein.di.compose.rememberFactory
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI
import ui.common.onboarding.data.OnboardingItem
import ui.core_ui.statusbar.StatusBarColorHelper
import ui.mobile.auth.AuthMainScreenMobile
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.mobile.verification.VerificationMainScreenMobile

@Composable
fun App(context: ComponentContext) = withDI(appModules) {
    val mainNavigatorFactory by rememberFactory<ComponentContext, MainNavigator>()
    val mainNavigator = mainNavigatorFactory(context)
    startLogger()
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(subscribeOnStatusBarColorChanges().value)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            AppContent(mainNavigator)
        }
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
                AuthMainScreenMobile(navigator)
            }
            is VerificationChild -> {
                VerificationMainScreenMobile()
            }
        }
    }
}

/**
 * SPLASH SCREEN
 */
@Composable
private fun AppSplashScreen(navigator: MainNavigator) = when (currentPlatform) {
    WEB -> SplashMainScreenMobile(navigator)
    DESKTOP -> SplashMainScreenMobile(navigator)
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

/**
 * SUBSCRIBE ON STATUS BAR COLOR CHANGES
 */
@Composable
private fun subscribeOnStatusBarColorChanges(): State<Color> {
    val statusBarColorHelper by rememberInstance<StatusBarColorHelper>()
    val startAppBackgroundColor = colors.backgroundColors.whiteBackgroundColor
    val color = remember { mutableStateOf(startAppBackgroundColor) }
    LaunchedEffect(Unit) {
        statusBarColorHelper.statusBarColor.collect { newColor ->
            color.value = newColor
        }
    }
    return color
}