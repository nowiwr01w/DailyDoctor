import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import components.snack_bar.SnackBar
import helpers.snack_bar.SnackBarHelper
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.VerificationChild
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import platform.Platform.*
import screens.auth.AuthMainScreenMobile
import screens.onboarding.OnboardingMainScreenMobile
import screens.pin_code.PinCodeMainScreenMobile
import screens.splash.SplashMainScreenMobile
import screens.verification.VerificationMainScreenMobile
import theme.AppTheme

@Composable
fun App(context: ComponentContext) {
    AppTheme {
        AppContent(context)
    }
}

@Composable
private fun AppContent(
    context: ComponentContext,
    snackBarHelper: SnackBarHelper = koinInject(),
    navigator: MainNavigator = koinInject { parametersOf(context) }
) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = navigator.stack,
        animation = stackAnimation { child -> child.instance.animation }
    ) {
        when (val child = it.instance) {
            is SplashChild -> {
                SplashMainScreenMobile(navigator = navigator)
            }
            is OnboardingChild -> OnboardingMainScreenMobile(
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
    subscribeOnSnackBar(snackBarHelper)()
}

/**
 * SUBSCRIBE ON SNACK BAR
 */
@Composable
private fun subscribeOnSnackBar(snackBarHelper: SnackBarHelper): @Composable () -> Unit = {
    val snackBarParams by snackBarHelper.params.collectAsState()
    val transition = updateTransition(snackBarParams)
    SnackBar(transition)
}