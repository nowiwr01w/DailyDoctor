package screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nowiwr01p.model.resources.component_with_resources.screens.splash.SplashScreenResources
import components.image.AppImage
import extensions.BaseScreen
import navigation.screen_results.ScreenResultKey.SelectLanguageResultKey
import navigation.screen_results.handleScreenResult
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SplashChild
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_logo_small
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
    handleScreenResult(SelectLanguageResultKey) { language ->
        viewModel.setEvent(InitAppDataAfterLanguageSet(language))
    }
    BaseScreen {
        SplashMainScreenContent(state)
    }
}

/**
 * CONTENT
 */
@Composable
private fun SplashMainScreenContent(state: State) = ConstraintLayout(
    modifier = Modifier
        .fillMaxSize()
        .background(colors.backgroundColors.whiteBackgroundColor)
) {
    val (logo, progress) = createRefs()
    AppImage(
        image = Res.drawable.ic_app_logo_small,
        modifier = Modifier
            .size(64.dp)
            .constrainAs(logo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
    )
    if (state.showProgress) {
        CircularProgressIndicator(
            color = colors.backgroundColors.redBackgroundColor,
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
