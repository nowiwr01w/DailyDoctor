package screens.subscription

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.AppToolbar
import components.button.StateButton
import components.image.AppImage
import extensions.BaseScreen
import extensions.advancedShadow
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_subscription_premium
import nowiwr01p.daily.doctor.resources.subscription_free_title
import nowiwr01p.daily.doctor.resources.subscription_toolbar_title
import observers.EffectObserver
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import subscription.SubscriptionContract.Effect
import subscription.SubscriptionContract.Event
import subscription.SubscriptionContract.Listener
import subscription.SubscriptionContract.State
import subscription.SubscriptionViewModel
import subscription.data.SubscriptionType
import subscription.data.SubscriptionType.*
import subscription.data.getSubscriptionItems
import theme.AppTheme
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
fun SubscriptionMainScreen(
    navigator: MainNavigator,
    viewModel: SubscriptionViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun chooseSubscriptionPlan(plan: SubscriptionType) {
            viewModel.setEvent(Event.ChooseSubscriptionPlan(plan))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is Effect.NavigateToHome -> {
                navigator.homeNavigator.navigateToHome()
            }
        }
    }

    BaseScreen {
        Content(
            state = viewModel.viewState.value,
            listener = listener
        )
    }
}

@Composable
private fun Content(
    state: State,
    listener: Listener?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        AppToolbar(
            title = Res.string.subscription_toolbar_title
        )
        SubscriptionContent(
            state = state,
            listener = listener
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SubscriptionContent(
    state: State,
    listener: Listener?
) {
    val density = LocalDensity.current
    val tabsItems = remember { getSubscriptionItems() }
    var bottomPadding by remember { mutableStateOf(0.dp) }
    var selectedTab: SubscriptionType by remember { mutableStateOf(Base) }
    val pagerState = rememberPagerState(
        initialPage = Base.position,
        pageCount = { tabsItems.size }
    )

    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(
            page = selectedTab.position,
            animationSpec = tween(durationMillis = 500)
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TabRow(
                selectedTabIndex = selectedTab.position,
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
                divider = {},
                indicator = {},
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(36.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                tabsItems.forEachIndexed { index, tab ->
                    val fontWeight = when (tab) {
                        selectedTab -> FontWeight.Medium
                        else -> FontWeight.Normal
                    }
                    val textColor = when (tab) {
                        selectedTab -> colors.textColors.whiteTextColor
                        else -> colors.textColors.blackTextColor
                    }
                    val backgroundColor = when (tab) {
                        selectedTab -> colors.backgroundColors.grayBackgroundColor
                        else -> colors.backgroundColors.grayBackgroundColor.copy(alpha = 0.075f)
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = if (index == tabsItems.lastIndex) 0.dp else 8.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable {
                                selectedTab = tab
                            }
                    ) {
                        Text(
                            text = stringResource(tab.name),
                            color = textColor,
                            style = MaterialTheme.typography.h6.copy(fontWeight = fontWeight),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { tabPosition ->
                val tab = tabsItems[tabPosition]
                Benefits(
                    item = tab,
                    bottomPadding = bottomPadding
                )
            }
        }

        SubscribeOrSkipBox(
            state = state,
            listener = listener,
            selectedSubscriptionPlan = selectedTab,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .onSizeChanged { size ->
                    bottomPadding = size.height.dp / density.density
                }
        )
    }
}

/**
 * BENEFITS
 */
@Composable
private fun Benefits(
    item: SubscriptionType,
    bottomPadding: Dp
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        val benefits = item.benefits
        items(benefits) { text ->
            BenefitItem(
                image = Res.drawable.ic_subscription_premium,
                title = Res.string.subscription_free_title,
                description = text
            )
        }
        item(
            span = StaggeredGridItemSpan.FullLine
        ) {
            Spacer(modifier = Modifier.height(bottomPadding + 24.dp))
        }
    }
}

@Composable
private fun BenefitItem(
    image: DrawableResource,
    title: StringResource,
    description: StringResource
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .advancedShadow(
                cornerRadius = 8.dp,
                outsideBlurRadius = 4.dp
            )
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = colors.backgroundColors.whiteBackgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppImage(
                image = image,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(title),
                color = colors.textColors.blackTextColor,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 8.dp, end = 16.dp)
            )
        }
        Text(
            text = stringResource(description),
            color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun SubscribeOrSkipBox(
    state: State,
    listener: Listener?,
    selectedSubscriptionPlan: SubscriptionType,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .advancedShadow(
                shadowColor = colors.textColors.blackTextColor.copy(0.25f),
                cornerRadius = 24.dp,
                outsideBlurRadius = 24.dp
            )
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = colors.backgroundColors.whiteBackgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        val text = when (selectedSubscriptionPlan) {
            Free -> "Продолжить без подписки"
            else -> "Продолжить за ${selectedSubscriptionPlan.basePriceUsd}$ в месяц"
        }
        StateButton(
            text = text,
            state = state.subscribeButtonState,
            onClick = { listener?.chooseSubscriptionPlan(selectedSubscriptionPlan) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = "Продолжая, вы соглашаетесь с политикой конфиденциальности и условиями пользования",
            color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = AppTheme {
    Content(
        state = State(),
        listener = null
    )
}