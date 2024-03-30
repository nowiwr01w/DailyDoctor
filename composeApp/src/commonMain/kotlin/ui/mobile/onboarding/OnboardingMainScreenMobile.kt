package ui.mobile.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import base.theme.AppTheme
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import navigation.auth.AuthNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.common.onboarding.OnboardingContract.*
import ui.common.onboarding.OnboardingViewModel
import ui.common.onboarding.data.OnboardingItem
import ui.common.onboarding.data.OnboardingItem.NotificationsOnboardingItem

@Composable
internal fun OnboardingMainScreenMobile(
    navigator: AuthNavigator,
    viewModel: OnboardingViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun showNextOnboardingItem() {
            // TODO: Navigate to the similar screen
        }
        override fun onEnableNotificationsClick() {
            // TODO: Enable notifications
        }
        override fun navigateToAuth() {
            // TODO: Navigate to auth screen
        }
    }
    
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }
    
    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is Effect.RequestNotifications -> {
                // TODO
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
    listener: Listener?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.currentOnboardingItem != null) {
            val currentOnboardingItem = state.currentOnboardingItem
            OnboardingItemView(
                item = currentOnboardingItem,
                onShowNextItemClicked = {
                    when (currentOnboardingItem) {
                        is NotificationsOnboardingItem -> listener?.navigateToAuth()
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
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        )
        Text(
            text = item.title,
            color = colors.textColors.blackTextColor,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(top = 8.dp)
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
private fun Preview() = AppTheme {
    OnboardingMainScreenContent(
        state = State(),
        listener = null
    )
}