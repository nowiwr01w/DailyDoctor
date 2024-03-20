package ui.mobile.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import base.theme.AppTheme
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.common.splash.SplashContract
import ui.common.splash.SplashContract.Event
import ui.common.splash.SplashContract.State
import ui.common.splash.SplashViewModel

@Composable
internal fun SplashMainScreenMobile(
    viewModel: SplashViewModel = rememberViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }
    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is SplashContract.Effect.NavigateToHome -> {
                // TODO
            }
        }
    }
    SplashMainScreenContent(state = viewModel.viewState.value)
}

/**
 * CONTENT
 */
@Composable
private fun SplashMainScreenContent(state: State) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = AppTheme {
    SplashMainScreenMobile()
}