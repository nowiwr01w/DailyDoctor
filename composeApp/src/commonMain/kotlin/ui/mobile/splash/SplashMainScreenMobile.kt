package ui.mobile.splash

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.theme.AppTheme
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_app_logo_small
import navigation.MainNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.common.onboarding.data.getOnboardingItems
import ui.common.splash.SplashContract
import ui.common.splash.SplashContract.Event
import ui.common.splash.SplashContract.SplashAnimationState
import ui.common.splash.SplashContract.State
import ui.common.splash.SplashViewModel

@Composable
internal fun SplashMainScreenMobile(
    navigator: MainNavigator,
    viewModel: SplashViewModel = rememberViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }
    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is SplashContract.Effect.NavigateToOnboarding -> {
                val onboardingItem = getOnboardingItems().first()
                navigator.onboardingNavigator.navigateToOnboarding(onboardingItem)
            }
        }
    }
    SplashMainScreenContent(state = viewModel.viewState.value)
}

/**
 * CONTENT
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
private fun SplashMainScreenContent(state: State) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        if (state.animationState == SplashAnimationState.PROGRESS) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp)
            )
        }
        AnimatedContainer(
            expectedAnimationState = SplashAnimationState.ICON,
            actualAnimationState = state.animationState
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_app_logo_small),
                contentDescription = "",
                modifier = Modifier.size(64.dp)
            )
        }
        AnimatedContainer(
            expectedAnimationState = SplashAnimationState.FIRST_TEXT,
            actualAnimationState = state.animationState,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            LogoText(
                text = "DAILY",
                color = colors.textColors.blueTextColor
            )
        }
        AnimatedContainer(
            expectedAnimationState = SplashAnimationState.SECOND_TEXT,
            actualAnimationState = state.animationState,
        ) {
            LogoText(
                text = "DOCTOR",
                color = colors.textColors.redTextColor
            )
        }
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
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        ),
    ) {
        content()
    }
}

/**
 * LOGO TEXT
 */
@Composable
private fun LogoText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
        letterSpacing = 1.5.sp,
        color = color,
        modifier = modifier
    )
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = AppTheme {
    SplashMainScreenContent(
        state = State()
    )
}