package screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import extensions.BaseScreen
import navigation.screen_results.ScreenResultKey.SelectLanguageResultKey
import navigation.screen_results.handleScreenResult
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SplashChild
import com.nowiwr01p.model.resources.component_with_resources.screens.splash.SplashScreenResources
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_logo_small
import org.jetbrains.compose.resources.painterResource
import splash.Effect.NavigateToHome
import splash.Effect.NavigateToOnboarding
import splash.Effect.ShowSelectLanguageDialog
import splash.Event.InitAppDataAfterLanguageSet
import splash.SplashViewModel
import splash.State
import splash.data.SplashAnimationState
import splash.data.SplashAnimationState.FIRST_TEXT
import splash.data.SplashAnimationState.ICON
import splash.data.SplashAnimationState.PROGRESS
import splash.data.SplashAnimationState.SECOND_TEXT
import theme.CustomTheme
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
internal fun SplashChild.SplashMainScreenMobile(
    navigator: MobileNavigator,
    resources: SplashScreenResources,
    viewModel: SplashViewModel = baseComponent.rememberViewModel()
) {
    val state = viewModel.getState { effect ->
        when (effect) {
            is NavigateToHome -> {
                navigator.screensNavigator.homeNavigator.navigateToHome()
            }
            is NavigateToOnboarding -> {
                navigator.screensNavigator.subscriptionNavigator.navigateToSubscription()
            }
            is ShowSelectLanguageDialog -> {
                navigator.dialogsNavigator.showSelectLanguageDialog(isFirstSelection = true)
            }
        }
    }
    handleScreenResult(SelectLanguageResultKey) {
        viewModel.setEvent(InitAppDataAfterLanguageSet)
    }
    BaseScreen {
        resources.SplashMainScreenContent(state)
    }
}

/**
 * CONTENT
 */
@Composable
private fun SplashScreenResources.SplashMainScreenContent(state: State) = ConstraintLayout(
    modifier = Modifier
        .fillMaxSize()
        .background(colors.backgroundColors.whiteBackgroundColor)
) {
    val (content, progress) = createRefs()

    val contentModifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .constrainAs(content) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = contentModifier
    ) {
        AppLogo(state)
        DailyText(state)
        DoctorText(state)
    }

    val progressModifier = Modifier
        .size(24.dp)
        .constrainAs(progress) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(content.bottom)
            bottom.linkTo(parent.bottom)
        }
    Progress(
        state = state,
        modifier = progressModifier
    )
}

/**
 * APP LOGO
 */
@Composable
private fun AppLogo(state: State) = AnimatedContainer(
    expectedAnimationState = ICON,
    actualAnimationState = state.animationState
) {
    Image(
        painter = painterResource(Res.drawable.ic_app_logo_small),
        contentDescription = "",
        modifier = Modifier.size(64.dp)
    )
}

/**
 * DAILY TEXT
 */
@Composable
private fun SplashScreenResources.DailyText(state: State) = AnimatedContainer(
    expectedAnimationState = FIRST_TEXT,
    actualAnimationState = state.animationState,
) {
    Text(
        text = appName.split(" ")[0].uppercase(),
        style = CustomTheme.typography.displayLarge,
        color = colors.textColors.blueTextColor,
        letterSpacing = 1.5.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
}

/**
 * DOCTOR TEXT
 */
@Composable
private fun SplashScreenResources.DoctorText(state: State) = AnimatedContainer(
    expectedAnimationState = SECOND_TEXT,
    actualAnimationState = state.animationState,
) {
    Text(
        text = appName.split(" ")[1].uppercase(),
        style = CustomTheme.typography.displayLarge,
        color = colors.textColors.redTextColor,
        letterSpacing = 1.5.sp
    )
}

@Composable
private fun Progress(
    state: State,
    modifier: Modifier
) {
    AnimatedContainer(
        modifier = modifier,
        expectedAnimationState = PROGRESS,
        actualAnimationState = state.animationState
    ) {
        CircularProgressIndicator(
            color = colors.backgroundColors.redBackgroundColor,
            strokeWidth = 3.dp
        )
    }
}

/**
 * ANIMATED CONTAINER
 */
@Composable
private fun AnimatedContainer(
    expectedAnimationState: SplashAnimationState,
    actualAnimationState: SplashAnimationState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = actualAnimationState.value >= expectedAnimationState.value,
        modifier = modifier,
        exit = ExitTransition.None,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = actualAnimationState.animationDuration,
                easing = LinearEasing
            )
        )
    ) {
        content()
    }
}
