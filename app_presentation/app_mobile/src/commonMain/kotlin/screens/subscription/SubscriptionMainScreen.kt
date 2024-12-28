package screens.subscription

 import androidx.compose.animation.AnimatedVisibility
 import androidx.compose.animation.animateColorAsState
 import androidx.compose.animation.animateContentSize
 import androidx.compose.animation.core.animateDpAsState
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
 import theme.CustomTheme
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
 import components.button.StateButton
 import components.image.AppImage
 import extensions.BaseScreen
 import extensions.advancedShadow
 import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
 import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.SubscriptionChild
 import nowiwr01p.daily.doctor.resources.Res
 import nowiwr01p.daily.doctor.resources.ic_drop_down_arrow
 import nowiwr01p.daily.doctor.resources.ic_sad_cat_placeholder
 import nowiwr01p.daily.doctor.resources.subscription_continue_accept_polices
 import nowiwr01p.daily.doctor.resources.subscription_continue_as_unsubscribed
 import nowiwr01p.daily.doctor.resources.subscription_continue_for
 import nowiwr01p.daily.doctor.resources.subscription_free_placeholder_description
 import nowiwr01p.daily.doctor.resources.subscription_free_placeholder_title
 import nowiwr01p.daily.doctor.resources.subscription_per_month
 import nowiwr01p.daily.doctor.resources.subscription_per_year
 import nowiwr01p.daily.doctor.resources.subscription_toolbar_title
 import nowiwr01p.daily.doctor.resources.subscription_year
 import observers.EffectObserver
 import org.jetbrains.compose.resources.stringResource
 import org.jetbrains.compose.ui.tooling.preview.Preview
 import subscription.SubscriptionContract.Effect
 import subscription.SubscriptionContract.Event
 import subscription.SubscriptionContract.Listener
 import subscription.SubscriptionContract.State
 import subscription.SubscriptionViewModel
 import subscription.data.BenefitData
 import subscription.data.SubscriptionPeriod
 import subscription.data.SubscriptionPeriod.Monthly
 import subscription.data.SubscriptionPeriod.Yearly
 import subscription.data.SubscriptionType
 import subscription.data.SubscriptionType.Base
 import subscription.data.SubscriptionType.Free
 import subscription.data.getSubscriptionItems
 import theme.CustomTheme.colors
 import view_model.rememberViewModel

@Composable
fun SubscriptionChild.SubscriptionMainScreen(
    navigator: MobileNavigator,
    viewModel: SubscriptionViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun selectSubscriptionPlan(plan: SubscriptionType) {
            viewModel.setEvent(Event.SelectSubscriptionPlan(plan))
        }
        override fun toggleSubscriptionPeriod(period: SubscriptionPeriod) {
            viewModel.setEvent(Event.ToggleSubscriptionPeriod(period))
        }
        override fun subscribeOrSkip() {
            viewModel.setEvent(Event.SubscribeOrSkip)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is Effect.NavigateToHome -> {
                navigator.screensNavigator.homeNavigator.navigateToHome()
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
        SubscriptionToolbar(
            state = state,
            listener = listener
        )
        SubscriptionContent(
            state = state,
            listener = listener
        )
    }
}

/**
 * TOOLBAR
 */
@Composable
private fun SubscriptionToolbar(
    state: State,
    listener: Listener?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        Text(
            text = stringResource(Res.string.subscription_toolbar_title),
            color = colors.textColors.blackTextColor,
            style = CustomTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        MonthlyYearlySwitch(
            state = state,
            listener = listener
        )
    }
}

/**
 * SUBSCRIPTION CONTENT
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SubscriptionContent(
    state: State,
    listener: Listener?
) {
    val selectedPlan = state.plan
    val density = LocalDensity.current
    val tabsItems = remember { getSubscriptionItems() }
    var bottomPadding by remember { mutableStateOf(0.dp) }
    val pagerState = rememberPagerState(
        initialPage = Base.position,
        pageCount = { tabsItems.size }
    )

    LaunchedEffect(selectedPlan) {
        pagerState.animateScrollToPage(
            page = selectedPlan.position,
            animationSpec = tween(durationMillis = 500)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TabRow(
                selectedTabIndex = selectedPlan.position,
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
                tabsItems.forEachIndexed { index, currentPlan ->
                    val fontWeight = when (currentPlan) {
                        selectedPlan -> FontWeight.Medium
                        else -> FontWeight.Normal
                    }
                    val textColor by animateColorAsState(
                        targetValue = when (currentPlan) {
                            selectedPlan -> colors.textColors.whiteTextColor
                            else -> colors.textColors.blackTextColor
                        },
                        animationSpec = tween(durationMillis = 500)
                    )
                    val backgroundColor by animateColorAsState(
                        targetValue = when (currentPlan) {
                            selectedPlan -> colors.backgroundColors.grayBackgroundColor
                            else -> Color(0xFFF2F1F1) // TODO: To colors
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
                            .clickable { listener?.selectSubscriptionPlan(currentPlan) }
                    ) {
                        Text(
                            text = stringResource(currentPlan.name),
                            color = textColor,
                            style = CustomTheme.typography.headlineSmall.copy(fontWeight = fontWeight),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
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
        text = stringResource(Res.string.subscription_free_placeholder_title),
        color = colors.textColors.blackTextColor,
        style = CustomTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
    )
    Text(
        text = stringResource(Res.string.subscription_free_placeholder_description),
        color = colors.textColors.blackTextColor.copy(alpha = 0.9f),
        style = CustomTheme.typography.headlineSmall,
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
                style = CustomTheme.typography.headlineMedium,
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
                style = CustomTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
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
        val text = when (state.plan) {
            Free -> {
                stringResource(Res.string.subscription_continue_as_unsubscribed)
            }
            else -> with(state) {
                buildString {
                    val continueText = stringResource(Res.string.subscription_continue_for)
                    append(continueText)
                    append(" ")
                    val price = if (period is Monthly) plan.monthlyPrice else plan.yearlyPrice
                    append(price.discountedPrice)
                    append("$ ")
                    val periodRes = when (period) {
                        is Monthly -> Res.string.subscription_per_month
                        is Yearly -> Res.string.subscription_per_year
                    }
                    val period = stringResource(periodRes).lowercase()
                    append(period)
                }
            }
        }
        StateButton(
            text = text,
            state = state.subscribeButtonState,
            onClick = { listener?.subscribeOrSkip() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = stringResource(Res.string.subscription_continue_accept_polices),
            color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
            style = CustomTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}

/**
 * MONTHLY YEARLY SWITCH
 */
@Composable
private fun MonthlyYearlySwitch(
    state: State,
    listener: Listener?
) {
    val innerCirclePadding by animateDpAsState(
        targetValue = if (state.period is Monthly) 3.dp else 25.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val backgroundColor by animateColorAsState(
        targetValue = when (state.period) {
            is Monthly -> Color(0xFFF2F1F1) // TODO: To colors
            is Yearly -> colors.backgroundColors.grayBackgroundColor
        },
        animationSpec = tween(durationMillis = 500)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(48.dp)
                .clip(CircleShape)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ) {
                    val plan = if (state.period is Monthly) Yearly else Monthly
                    listener?.toggleSubscriptionPeriod(plan)
                }
        ) {
            Box(
                modifier = Modifier
                    .padding(start = innerCirclePadding, top = 3.dp, bottom = 3.dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(
                        color = colors.backgroundColors.whiteBackgroundColor,
                        shape = CircleShape
                    )
            )
        }
        Text(
            text = stringResource(Res.string.subscription_year),
            color = colors.textColors.blackTextColor,
            style = CustomTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
