
import Platform.DESKTOP
import Platform.WEB
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.AppTheme
import base.theme.CustomTheme.colors
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import di.appModules
import di.koinModules
import navigation.MainNavigator
import navigation.MainNavigator.Child.AuthChild
import navigation.MainNavigator.Child.OnboardingChild
import navigation.MainNavigator.Child.PinCodeChild
import navigation.MainNavigator.Child.SplashChild
import navigation.MainNavigator.Child.VerificationChild
import org.kodein.di.compose.rememberFactory
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI
import org.koin.core.context.startKoin
import ui.common.onboarding.data.OnboardingItem
import ui.core_ui.components.snack_bar.SnackBar
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import ui.core_ui.helpers.window_insets.AppWindowColorsHelper
import ui.core_ui.helpers.window_insets.WindowColorsData
import ui.core_ui.helpers.window_insets.data.LocalWindowInsets
import ui.mobile.auth.AuthMainScreenMobile
import ui.mobile.onboarding.OnboardingMainScreenMobile
import ui.mobile.pin_code.PinCodeMainScreenMobile
import ui.mobile.splash.SplashMainScreenMobile
import ui.mobile.verification.VerificationMainScreenMobile

fun initKoin() = startKoin {
    modules(koinModules)
}

@Composable
fun App(context: ComponentContext) = withDI(appModules) {
    initKoin()
    val mainNavigatorFactory by rememberFactory<ComponentContext, MainNavigator>()
    val mainNavigator = mainNavigatorFactory(context)
    startLogger()
    AppTheme {
        AppContentFullScreen(mainNavigator)
    }
}

@Composable
private fun AppContentFullScreen(navigator: MainNavigator) = ConstraintLayout(
    modifier = Modifier.fillMaxSize()
) {
    val windowInsets = LocalWindowInsets.current
    val (statusBar, navigationBar, content) = createRefs()
    val (statusBarColor, navigationBarColor) = subscribeOnAppWindowColorsChanges().value

    val statusBarModifier = Modifier
        .fillMaxWidth()
        .height(windowInsets.topPadding)
        .background(statusBarColor)
        .constrainAs(statusBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Box(modifier = statusBarModifier)

    val navigationBarModifier = Modifier
        .fillMaxWidth()
        .height(windowInsets.bottomPadding)
        .background(navigationBarColor)
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
        navigator = navigator,
        modifier = appContentModifier
    )
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
private fun subscribeOnAppWindowColorsChanges(): State<WindowColorsData> {
    val appWindowColorsHelper by rememberInstance<AppWindowColorsHelper>()
    val appWindowColorsData = WindowColorsData(
        statusBarColor = colors.backgroundColors.whiteBackgroundColor,
        navigationBarColor = colors.backgroundColors.whiteBackgroundColor
    )
    val appWindowColors = remember {
        mutableStateOf(appWindowColorsData)
    }
    LaunchedEffect(Unit) {
        appWindowColorsHelper.appWindowColors.collect { newColors ->
            if (newColors != null) {
                appWindowColors.value = newColors
            }
        }
    }
    return appWindowColors
}

/**
 * SUBSCRIBE ON SNACK BAR
 */
private fun subscribeOnSnackBar(): @Composable () -> Unit = {
    val snackBarHelper by rememberInstance<SnackBarHelper>()
    val snackBarParams by snackBarHelper.params.collectAsState()
    val transition = updateTransition(snackBarParams)
    SnackBar(transition)
}