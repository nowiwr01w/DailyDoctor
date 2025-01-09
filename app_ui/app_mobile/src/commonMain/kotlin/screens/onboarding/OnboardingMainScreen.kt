package screens.onboarding

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Notifications
import components.image.RemoteImage
import extensions.BaseScreen
import getScreenWidth
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.child.MobileScreensChild.OnboardingChild
import onboarding.Effect.NavigateToAuth
import onboarding.Effect.ShowEnableNotificationsDialog
import onboarding.Effect.SlideToNextOnboardingItem
import onboarding.Event.RequestNotifications
import onboarding.Event.ShowNextOnboardingItem
import onboarding.Listener
import onboarding.OnboardingViewModel
import onboarding.State
import theme.CustomTheme
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
internal fun OnboardingChild.OnboardingMainScreenMobile(
    navigator: MobileNavigator,
    viewModel: OnboardingViewModel = baseComponent.rememberViewModel()
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 5 } // TODO: To constants

    val listener = object : Listener {
        override fun showNextOnboardingItem(currentOnboardingItem: OnboardingItem) {
            viewModel.setEvent(ShowNextOnboardingItem(currentOnboardingItem))
        }
        override fun onEnableNotificationsClick() {
            viewModel.setEvent(RequestNotifications)
        }
    }
    val state = viewModel.getState { effect ->
        when (effect) {
            is ShowEnableNotificationsDialog -> {
                navigator.screensNavigator.authNavigator.navigateToAuth() // TODO
            }
            is NavigateToAuth -> {
                navigator.screensNavigator.authNavigator.navigateToAuth()
            }
            is SlideToNextOnboardingItem -> scope.launch {
                pagerState.animateScrollToPage(
                    page = effect.nextOnboardingItemIndex,
                    animationSpec = tween()
                )
            }
        }
    }
    BaseScreen {
        Content(
            state = state,
            listener = listener,
            pagerState = pagerState
        )
    }
}

/**
 * CONTENT
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    state: State,
    listener: Listener?,
    pagerState: PagerState
) {
    if (state.onboardingItems.isEmpty()) {
        LoadingContent()
    } else {
        SuccessContent(
            state = state,
            listener = listener,
            pagerState = pagerState
        )
    }
}

@Composable
private fun LoadingContent() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    CircularProgressIndicator(
        color = colors.backgroundColors.redBackgroundColor,
        strokeWidth = 3.dp
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SuccessContent(
    state: State,
    listener: Listener?,
    pagerState: PagerState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val item = state.onboardingItems[page]
            OnboardingItemView(
                item = item.data,
                onShowNextItemClicked = {
                    when (item.type) {
                        is Notifications -> listener?.onEnableNotificationsClick()
                        else -> listener?.showNextOnboardingItem(item)
                    }
                }
            )
        }
    }
}

/**
 * ONBOARDING ITEM
 */
 @Composable
private fun OnboardingItemView(
    item: OnboardingItemData,
    onShowNextItemClicked: () -> Unit
) {
    val iconWidth by rememberUpdatedState(0.8 * getScreenWidth())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RemoteImage(
            url = item.image,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(iconWidth)
        )
        Text(
            text = item.title,
            color = colors.textColors.blackTextColor,
            style = CustomTheme.typography.displaySmall,
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .clip(CustomTheme.shapes.large)
                .background(
                    color = colors.backgroundColors.blueBackgroundColor,
                    shape = CustomTheme.shapes.large
                )
        ) {
            Text(
                text = item.description,
                color = colors.textColors.grayTextColor,
                style = CustomTheme.typography.bodyLarge,
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
                .clip(CustomTheme.shapes.large)
        ) {
            Text(text = item.firstButtonText)
        }
    }
}
