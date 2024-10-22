package screens.subscription

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.AppToolbar
import components.button.StateButton
import components.image.AppImage
import extensions.BaseScreen
import extensions.advancedShadow
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_drop_down_arrow
import nowiwr01p.daily.doctor.resources.ic_sad_cat_placeholder
import nowiwr01p.daily.doctor.resources.subscription_toolbar_title
import observers.EffectObserver
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import subscription.SubscriptionContract.Effect
import subscription.SubscriptionContract.Event
import subscription.SubscriptionContract.Listener
import subscription.SubscriptionContract.State
import subscription.SubscriptionViewModel
import subscription.data.BenefitData
import subscription.data.SubscriptionType
import subscription.data.SubscriptionType.Base
import subscription.data.SubscriptionType.Free
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

/**
 * CONTENT
 */
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
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(36.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                tabsItems.forEachIndexed { index, tab ->
                    val fontWeight = when (tab) {
                        selectedTab -> FontWeight.Medium
                        else -> FontWeight.Normal
                    }
                    val textColor by animateColorAsState(
                        targetValue = when (tab) {
                            selectedTab -> colors.textColors.whiteTextColor
                            else -> colors.textColors.blackTextColor
                        },
                        animationSpec = tween(durationMillis = 500)
                    )
                    val backgroundColor by animateColorAsState(
                        targetValue = when (tab) {
                            selectedTab -> colors.backgroundColors.grayBackgroundColor
                            else -> Color(0xFFF2F1F1)
                        },
                        animationSpec = tween(durationMillis = 500)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = if (index == tabsItems.lastIndex) 0.dp else 8.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable { selectedTab = tab }
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
                tabsItems[tabPosition].let { tab ->
                    when (tab) {
                        is Free -> {
                            SadCatPlaceholder(bottomPadding)
                        }
                        else -> Benefits(
                            item = tab,
                            bottomPadding = bottomPadding
                        )
                    }
                }
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
 * SAD CAT PLACEHOLDER
 */
@Composable
private fun SadCatPlaceholder(bottomPadding: Dp) = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .padding(bottom = bottomPadding)
        .fillMaxSize()
) {
    AppImage(
        image = Res.drawable.ic_sad_cat_placeholder,
        modifier = Modifier.size(225.dp)
    )
    Text(
        text = "Этот проект был создан одним человеком",
        color = colors.textColors.blackTextColor,
        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
    )
    Text(
        text = "Обычно проекты такого масштаба создаются целыми командами разработчиков. Я же создал его самостоятельно, вкладывая много времени и усилий. Если вам нравится то, что я делаю, было бы здорово, если бы вы поддержали проект финансово. Это поможет развивать его дальше и делать его ещё лучше для вас",
        color = colors.textColors.blackTextColor.copy(alpha = 0.9f),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    )
}

/**
 * BENEFITS
 */
@Composable
private fun Benefits(
    item: SubscriptionType,
    bottomPadding: Dp
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        val items = item.benefits + item.benefits // TODO
        itemsIndexed(items) { index, data ->
            if (index == 0) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            BenefitItem(data)
            Spacer(
                modifier = Modifier.height(
                    height = if (index != items.lastIndex) 8.dp else bottomPadding + 16.dp
                )
            )
        }
    }
}

@Composable
private fun BenefitItem(data: BenefitData) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationDegrees by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween()
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .advancedShadow(
                cornerRadius = 16.dp,
                outsideBlurRadius = 4.dp
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = colors.backgroundColors.whiteBackgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .animateContentSize(animationSpec = tween())
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                isExpanded = !isExpanded
            }
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 36.dp)
        ) {
            Text(
                text = stringResource(data.title),
                color = colors.textColors.blackTextColor,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 16.dp)
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .clickable { isExpanded = !isExpanded }
            ) {
                AppImage(
                    image = Res.drawable.ic_drop_down_arrow,
                    color = Color(0xFF949494), // TODO: Add to colors
                    modifier = Modifier
                        .size(14.dp)
                        .rotate(rotationDegrees)
                )
            }
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(animationSpec = tween()),
            exit = fadeOut(animationSpec = tween())
        ) {
            Text(
                text = stringResource(data.description),
                color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier.padding(bottom = 9.dp)
            )
        }
    }
}

/**
 * SUBSCRIBE BUTTON
 */
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
                shadowColor = colors.textColors.blackTextColor.copy(0.4f),
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