package screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import components.image.AppImage
import navigation.screen_results.ScreenResultKey.SelectLanguageResultKey
import navigation.screen_results.handleScreenResult
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.child.MobileScreensChild.SplashChild
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_launcher_foreground
import splash.Effect.NavigateToHome
import splash.Effect.NavigateToOnboarding
import splash.Effect.ShowSelectLanguageDialog
import splash.Event.InitAppDataAfterLanguageSet
import splash.SplashViewModel
import splash.State
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
internal fun SplashChild.SplashMainScreenMobile(
    navigator: MobileNavigator,
    viewModel: SplashViewModel = baseComponent.rememberViewModel()
) {
    val state = viewModel.getState { effect ->
        when (effect) {
            is NavigateToHome -> {
                navigator.screensNavigator.homeNavigator.navigateToHome()
            }
            is NavigateToOnboarding -> {
                navigator.screensNavigator.onboardingNavigator.navigateToOnboarding()
            }
            is ShowSelectLanguageDialog -> {
                navigator.dialogsNavigator.showSelectLanguageDialog(isFirstSelection = true)
            }
        }
    }
    handleScreenResult(SelectLanguageResultKey) { language ->
        viewModel.setEvent(InitAppDataAfterLanguageSet(language))
    }
    SplashMainScreenContent(state)
}

/**
 * CONTENT
 */
@Composable
private fun SplashMainScreenContent(state: State) = ConstraintLayout(
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCD201F))
) {
    val (logo, progress) = createRefs()
    AppImage(
        image = Res.drawable.ic_app_launcher_foreground,
        color = Color.White,
        modifier = Modifier
            .size(288.dp)
            .constrainAs(logo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
    )
    if (state.showProgress) {
        CircularProgressIndicator(
            color = colors.backgroundColors.whiteBackgroundColor,
            strokeWidth = 3.dp,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(progress) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(logo.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
