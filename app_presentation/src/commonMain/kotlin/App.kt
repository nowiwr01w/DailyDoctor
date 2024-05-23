import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.AppTheme
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.VerificationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import platform.Platform.*
import platform.currentPlatform
import ui.core_ui.components.snack_bar.SnackBar
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import ui.core_ui.helpers.window_insets.LocalWindowInsets
import ui.mobile.auth.AuthMainScreenMobile
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.pin_code.PinCodeMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.mobile.verification.VerificationMainScreenMobile

@Composable
fun App(context: ComponentContext) {
    startLogger()
    AppTheme {
        AppContentFullScreen(context)
    }
}

@Composable
private fun AppContentFullScreen(
    context: ComponentContext,
    mainNavigator: MainNavigator = koinInject { parametersOf(context) }
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val windowInsets = LocalWindowInsets.current
        val (statusBar, navigationBar, content) = createRefs()

        val statusBarModifier = Modifier // TODO: Create base function and use on each screen
            .fillMaxWidth()
            .height(windowInsets.topPadding)
            .constrainAs(statusBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        Box(modifier = statusBarModifier)

        val navigationBarModifier = Modifier
            .fillMaxWidth()
            .height(windowInsets.bottomPadding)
            .constrainAs(navigationBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        Box(modifier = navigationBarModifier)

        val appContentModifier = Modifier.constrainAs(content) {
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(statusBar.bottom)
            bottom.linkTo(navigationBar.top)
        }
        AppContent(
            navigator = mainNavigator,
            modifier = appContentModifier
        )
    }
}

@Composable
private fun AppContent(
    navigator: MainNavigator,
    modifier: Modifier
) {
    Children(
        modifier = modifier,
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
            is VerificationChild -> VerificationMainScreenMobile(
                email = child.email,
                verificationToken = child.verificationToken,
                navigator = navigator
            )
            is PinCodeChild -> PinCodeMainScreenMobile(
                navigator = navigator,
                mode = child.pinCodeMode
            )
        }
    }
    subscribeOnSnackBar()() // (*)(*)
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
    onboardingItem: OnboardingItemModel
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
 * SUBSCRIBE ON SNACK BAR
 */
@Composable
private fun subscribeOnSnackBar(
    snackBarHelper: SnackBarHelper = koinInject()
): @Composable () -> Unit = {
    val snackBarParams by snackBarHelper.params.collectAsState()
    val transition = updateTransition(snackBarParams)
    SnackBar(transition)
}