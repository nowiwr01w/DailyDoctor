package screens.pin_code

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.button.ButtonState
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.SUCCESS
import extensions.BaseScreen
import getScreenWidth
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.PinCodeChild
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_logo_small
import nowiwr01p.daily.doctor.resources.ic_delete
import nowiwr01p.daily.doctor.resources.ic_fingerprint
import nowiwr01p.daily.doctor.resources.pin_code_enter
import nowiwr01p.daily.doctor.resources.yo
import observers.EffectObserver
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pin_code.PinCodeContract.Effect.NavigateBack
import pin_code.PinCodeContract.Effect.NavigateToHome
import pin_code.PinCodeContract.Event
import pin_code.PinCodeContract.Listener
import pin_code.PinCodeContract.State
import pin_code.PinCodeViewModel
import pin_code.data.PinCodeData
import pin_code.data.PinCodeData.PinCodeDigit
import pin_code.data.PinCodeData.PinCodeIcon
import pin_code.data.PinCodeIconType.BIOMETRIC
import pin_code.data.PinCodeIconType.REMOVE_DIGIT
import pin_code.data.PinCodeOperation
import pin_code.data.PinCodeOperation.AddDigit
import pin_code.data.PinCodeOperation.RemoveDigit
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
fun PinCodeChild.PinCodeMainScreenMobile(
    navigator: MobileNavigator,
    viewModel: PinCodeViewModel = rememberViewModel(pinCodeMode)
) {
    val listener = object : Listener {
        override fun handleUserInput(operation: PinCodeOperation) {
            viewModel.setEvent(Event.HandleUserInput(operation))
        }
        override fun requestBiometric() {
            // TODO: Some expect/actual hard things
        }
    }

    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            NavigateBack -> {
                // TODO: Use when change or delete pin code
            }
            NavigateToHome -> {
                navigator.screensNavigator.subscriptionNavigator.navigateToSubscription()
            }
        }
    }

    BaseScreen {
        PinCodeMainScreenMobileContent(
            state = viewModel.viewState.value,
            listener = listener
        )
    }
}

/**
 * CONTENT
 */
@Composable
private fun PinCodeMainScreenMobileContent(
    state: State,
    listener: Listener
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        val (appLogo, userImage, text, dots, pinCode) = createRefs()

        val appLogoModifier = Modifier
            .padding(top = 16.dp)
            .size(32.dp)
            .constrainAs(appLogo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        AppLogo(modifier = appLogoModifier)

        val dotsModifier = Modifier.constrainAs(dots) {
            width = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        Dots(
            state = state,
            modifier = dotsModifier
        )

        val textModifier = Modifier
            .padding(bottom = 24.dp)
            .constrainAs(text) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(dots.top)
            }
        PinCodeText(
            state = state,
            modifier = textModifier
        )

        val userImageModifier = Modifier
            .padding(bottom = 16.dp)
            .size(96.dp)
            .clip(CircleShape)
            .constrainAs(userImage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(text.top)
            }
        UserImage(
            state = state,
            modifier = userImageModifier
        )

        val pinCodeContentModifier = Modifier
            .padding(bottom = 16.dp)
            .constrainAs(pinCode) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        PinCodeView(
            state = state,
            listener = listener,
            modifier = pinCodeContentModifier
        )
    }
}

/**
 * APP LOGO
 */
@Composable
private fun AppLogo(modifier: Modifier) = Image(
    painter = painterResource(Res.drawable.ic_app_logo_small),
    contentDescription = "App logo small",
    modifier = modifier
)

/**
 * USER IMAGE
 */
@Composable
private fun UserImage(
    state: State,
    modifier: Modifier
) {
    Image(
        painter = painterResource(Res.drawable.yo),
        contentDescription = "User profile image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

/**
 * ENTER OR CHANGE PIN CODE TEXT
 */
@Composable
private fun PinCodeText(
    state: State,
    modifier: Modifier
) {
    Text(
        text = stringResource(Res.string.pin_code_enter),
        color = colors.textColors.blackTextColor,
        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium),
        modifier = modifier
    )
}

/**
 * COUNT OF ENTERED SYMBOLS
 */
@Composable
private fun Dots(
    state: State,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) { index ->
            DotItem(
                isActive = index + 1 <= state.pinCode.length,
                buttonState = state.buttonState,
                modifier = Modifier.padding(start = if (index == 0) 0.dp else 18.dp)
            )
        }
    }
}

@Composable
private fun DotItem(
    isActive: Boolean,
    buttonState: ButtonState,
    modifier: Modifier
) {
    val color = when (buttonState) {
        DARK_GRAY_ACTIVE -> colors.backgroundColors.grayBackgroundColor.copy(
            alpha = if (isActive) 1f else 0.2f
        )
        DARK_GRAY_PROGRESS -> Color(0xFF16A34A) // TODO: To colors
        SUCCESS -> Color(0xFF16A34A)
        else -> Color(0xFFE34446)
    }
    val backgroundColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween()
    )
    Box(
        modifier = modifier
            .size(13.dp)
            .clip(CircleShape)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
    )
}

/**
 * PIN CODE CONTENT
 */
@Composable
private fun PinCodeView(
    state: State,
    listener: Listener,
    modifier: Modifier
) {
    val gridWidth = 3 * 88.dp + 32.dp
    val horizontalPadding = (getScreenWidth() - gridWidth) / 2
    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = horizontalPadding),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = false
    ) {
        items(pinCodeData) { data ->
            PinCodeViewItem(
                data = data,
                state = state,
                listener = listener
            )
        }
    }
}

@Composable
private fun PinCodeViewItem(
    data: PinCodeData,
    state: State,
    listener: Listener
) {
    val onItemClicked: () -> Unit = {
        when (data) {
            is PinCodeDigit -> {
                val operation = AddDigit(data.value)
                listener.handleUserInput(operation)
            }
            is PinCodeIcon -> when (data.type) {
                BIOMETRIC -> { listener.requestBiometric() }
                REMOVE_DIGIT -> { listener.handleUserInput(RemoveDigit) }
            }
        }
    }
    val isItemClickEnabled = when {
        state.pinCode.length < 4 -> when (data) {
            is PinCodeDigit -> true
            is PinCodeIcon -> {
                data.type == BIOMETRIC || data.type == REMOVE_DIGIT
            }
            else -> false
        }
        else -> false
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable(enabled = isItemClickEnabled) {
                onItemClicked()
            }
    ) {
        when (data) {
            is PinCodeDigit -> Text(
                text = data.value,
                color = colors.textColors.blackTextColor,
                style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.Medium)
            )
            is PinCodeIcon -> Image(
                painter = painterResource(data.value),
                contentDescription = "Pin table icon",
                colorFilter = ColorFilter.tint(
                    colors.backgroundColors.grayBackgroundColor.copy(alpha = 0.33f)
                ),
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

private val pinCodeData = listOf(
    PinCodeDigit("1"),
    PinCodeDigit("2"),
    PinCodeDigit("3"),
    PinCodeDigit("4"),
    PinCodeDigit("5"),
    PinCodeDigit("6"),
    PinCodeDigit("7"),
    PinCodeDigit("8"),
    PinCodeDigit("9"),
    PinCodeIcon(
        type = BIOMETRIC,
        value = Res.drawable.ic_fingerprint
    ),
    PinCodeDigit("0"),
    PinCodeIcon(
        type = REMOVE_DIGIT,
        value = Res.drawable.ic_delete
    )
)
