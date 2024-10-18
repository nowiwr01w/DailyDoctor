package screens.subscription

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.AppToolbar
import components.LocalWindowInsets
import components.bottom_sheet.BottomSheetParams
import components.bottom_sheet.BottomSheetSize.MAX_SIZE
import components.bottom_sheet.ShowBottomSheetHelper
import components.button.StateButton
import components.divider.NavigationBarSpacer
import components.image.AppImage
import components.text.AutoSizeText
import extensions.BaseScreen
import extensions.advancedShadow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_subscription_premium
import nowiwr01p.daily.doctor.resources.sad_cat
import nowiwr01p.daily.doctor.resources.subscription_free_title
import nowiwr01p.daily.doctor.resources.subscription_toolbar_title
import observers.EffectObserver
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import subscription.SubscriptionContract.*
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
        Toolbar()
        CatImage()
        if (state.showInitProgress) {
            LoadingContent()
        } else {
            BenefitsBottomSheet(state, listener)
        }
    }
}

/**
 * LOADING PROGRESS
 */
@Composable
private fun LoadingContent() = Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    CircularProgressIndicator(
        modifier = Modifier.size(24.dp),
        color = colors.backgroundColors.redBackgroundColor,
        strokeWidth = 3.dp
    )
}

/**
 * TOOLBAR
 */
@Composable
private fun Toolbar() {
    AppToolbar(title = Res.string.subscription_toolbar_title)
}

/**
 * CAT IMAGE
 */
@Composable
private fun CatImage() = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) {
    AppImage(
        image = Res.drawable.sad_cat,
        modifier = Modifier
            .fillMaxWidth()
            .height(189.dp)
    )
}

/**
 * BENEFITS BOTTOM SHEET
 */
@Composable
private fun BenefitsBottomSheet(
    state: State,
    listener: Listener?,
    helper: ShowBottomSheetHelper = koinInject()
) {
    val insets = LocalWindowInsets.current
    val topPadding by rememberUpdatedState(
        newValue = 265.dp - insets.topPadding
    )
    val params = BottomSheetParams(
        topPadding = topPadding,
        showDraggableElement = false,
        bottomSheetSize = MAX_SIZE,
        content = {
            BottomSheetContent(state, listener)
        }
    )
    LaunchedEffect(insets.topPadding) {
        if (insets.topPadding.value != 0f) {
            helper.showBottomSheet(params)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomSheetContent(
    state: State,
    listener: Listener?
) {
    val density = LocalDensity.current
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (tabs, benefits, subscribeBox) = createRefs()

        val scope = rememberCoroutineScope()
        val tabsItems = remember {
            getSubscriptionItems()
        }
        val pagerState = rememberPagerState(
            initialPage = Base.position,
            pageCount = { tabsItems.size }
        )
        var selectedTab: SubscriptionType by remember {
            mutableStateOf(Base)
        }

        val tabsModifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .constrainAs(tabs) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        TabRow(
            selectedTabIndex = selectedTab.position,
            backgroundColor = Color.White,
            contentColor = Color.Black,
            modifier = tabsModifier
        ) {
            tabsItems.forEach { tab ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        scope.launch {
                            pagerState.scrollToPage(tab.position)
                        }
                    }
                ) {
                    AppImage(
                        image = tab.icon,
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = stringResource(tab.name),
                        color = colors.textColors.blackTextColor,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                }
            }
        }

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }
                .distinctUntilChanged()
                .collect { page ->
                    selectedTab = when (page) {
                        0 -> Free
                        1 -> Base
                        2 -> Standard
                        else -> Premium
                    }
                }
        }

        val benefitsModifier = Modifier.constrainAs(benefits) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(tabs.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        var bottomPadding by remember {
            mutableStateOf(0.dp)
        }
        HorizontalPager(
            state = pagerState,
            modifier = benefitsModifier
        ) { tabPosition ->
            val tab = tabsItems[tabPosition]
            Benefits(
                item = tab,
                bottomPadding = bottomPadding
            )
        }

        SubscribeOrSkipBox(
            state = state,
            listener = listener,
            selectedSubscriptionPlan = selectedTab,
            modifier = Modifier
                .fillMaxWidth()
                .advancedShadow(
                    shadowColor = colors.textColors.blackTextColor.copy(0.5f),
                    cornerRadius = 24.dp,
                    outsideBlurRadius = 16.dp
                )
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(
                    color = colors.backgroundColors.whiteBackgroundColor,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .constrainAs(subscribeBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Продолжая, вы соглашаетесь с политикой конфиденциальности и условиями пользования",
            color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(0.5f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 0.75.dp,
                        color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {  }
            ) {
                Crossfade(
                    targetState = state.continueButtonSeconds == 0,
                    animationSpec = tween(durationMillis = 500)
                ) { isTimerEnded ->
                    val text = when {
                        isTimerEnded -> "Продолжить без подписки"
                        else -> "Продолжить без подписки\nчерез ${state.continueButtonSeconds} сек"
                    }
                    AutoSizeText(
                        text = text,
                        color = colors.textColors.blackTextColor.copy(alpha = 0.75f),
                        maxLines = 2,
                        style = MaterialTheme.typography.subtitle1.copy(
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            StateButton(
                text = "Подписаться",
                state = state.subscribeButtonState,
                onClick = { listener?.chooseSubscriptionPlan(selectedSubscriptionPlan) },
                modifier = Modifier
                    .weight(0.5f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
        NavigationBarSpacer()
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