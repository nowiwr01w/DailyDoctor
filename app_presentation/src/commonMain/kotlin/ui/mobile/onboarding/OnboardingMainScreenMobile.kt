package ui.mobile.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import base.theme.AppTheme
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import navigation.MainNavigator
import nowiwr01p.daily.doctor.app_presentation.theme.CustomTheme.colors
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import platform.getScreenWidth
import ui.common.onboarding.OnboardingContract.Effect
import ui.common.onboarding.OnboardingContract.Event
import ui.common.onboarding.OnboardingContract.Listener
import ui.common.onboarding.OnboardingContract.State
import ui.common.onboarding.OnboardingViewModel
import ui.common.onboarding.data.OnboardingItem
import ui.common.onboarding.data.OnboardingItem.NotificationsOnboardingItem
import ui.core_ui.helpers.window_insets.AppWindowColorsHelper
import ui.core_ui.helpers.window_insets.WindowColorsData
import org.koin.compose.koinInject

@Composable
internal fun OnboardingMainScreenMobile(
    onboardingItem: OnboardingItem,
    navigator: MainNavigator,
    appWindowColorsHelper: AppWindowColorsHelper = koinInject(),
    viewModel: OnboardingViewModel = rememberViewModel()
) {
    val onboardingScreenColors = with(colors.backgroundColors) {
        WindowColorsData(whiteBackgroundColor, whiteBackgroundColor)
    }
    val authScreenColors = with(colors.backgroundColors) {
        WindowColorsData(grayBackgroundColor, whiteBackgroundColor)
    }

    val listener = object : Listener {
        override fun showNextOnboardingItem() {
            viewModel.setEvent(Event.ShowNextOnboardingItem)
        }
        override fun onEnableNotificationsClick() {
            appWindowColorsHelper.setAppWindowColors(authScreenColors)
            navigator.authNavigator.navigateToAuth()
        }
    }
    
    LaunchedEffect(Unit) {
        appWindowColorsHelper.setAppWindowColors(onboardingScreenColors)
        viewModel.setEvent(Event.Init(onboardingItem))
    }
    
    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is Effect.RequestNotifications -> {
                // TODO
            }
            is Effect.NavigateToAuth -> {

            }
            is Effect.NavigateToNextOnboardingItem -> {
                navigator.onboardingNavigator.navigateToOnboarding(effect.onboardingItem)
            }
        }
    }
    
    OnboardingMainScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

/**
 * CONTENT
 */
@Composable
private fun OnboardingMainScreenContent(
    state: State,
    listener: Listener?
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        if (state.currentOnboardingItem != null) {
            val currentOnboardingItem = state.currentOnboardingItem
            OnboardingItemView(
                item = currentOnboardingItem,
                onShowNextItemClicked = {
                    when (currentOnboardingItem) {
                        is NotificationsOnboardingItem -> listener?.onEnableNotificationsClick()
                        else -> listener?.showNextOnboardingItem()
                    }
                }
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

/**
 * ONBOARDING ITEM
 */
 @OptIn(ExperimentalResourceApi::class)
 @Composable
private fun OnboardingItemView(
    item: OnboardingItem,
    onShowNextItemClicked: () -> Unit
) {
    val iconWidth by rememberUpdatedState(0.8 * getScreenWidth())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(iconWidth)
        )
        Text(
            text = item.title,
            color = colors.textColors.blackTextColor,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(
                    color = colors.backgroundColors.blueBackgroundColor,
                    shape = MaterialTheme.shapes.large
                )
        ) {
            Text(
                text = item.description,
                color = colors.textColors.grayTextColor,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 64.dp)
            )
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                onShowNextItemClicked()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = colors.textColors.whiteTextColor,
                backgroundColor = colors.backgroundColors.redBackgroundColor
            ),
            modifier = Modifier
                .padding(top = if (item.secondButtonText.isNotEmpty()) 48.dp else 0.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(MaterialTheme.shapes.large)
        ) {
            Text(item.firstButtonText)
        }
    }
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = base.theme.AppTheme {
    OnboardingMainScreenContent(
        state = State(),
        listener = null
    )
}