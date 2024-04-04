package ui.mobile.verification

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.CustomTheme.colors
import base.view_model.rememberViewModel
import platform.getScreenWidth
import ui.common.verification.VerificationContract.Event
import ui.common.verification.VerificationContract.Listener
import ui.common.verification.VerificationContract.State
import ui.common.verification.VerificationViewModel
import ui.core_ui.components.AutoSizeText
import ui.core_ui.components.ButtonState
import ui.core_ui.components.StateButton
import ui.mobile.auth.TopIcon
import ui.mobile.auth.TopTitle

@Composable
internal fun VerificationMainScreenMobile(
    viewModel: VerificationViewModel = rememberViewModel()
) {
    val listener = object : Listener {

    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    VerificationMainScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

/**
 * CONTENT
 */
@Composable
private fun VerificationMainScreenContent(
    state: State,
    listener: Listener
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.grayBackgroundColor)
    ) {
        val (icon, inputFieldsContainer) = createRefs()

        val isKeyboardOpen = false // TODO: expect function for iOS
        val authContentTransitionDp by animateDpAsState(
            targetValue = if (isKeyboardOpen) 8.dp else 160.dp
        )

        val iconModifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .constrainAs(icon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 32.dp)
            }
        TopIcon(modifier = iconModifier)

        val authContentModifier = Modifier
            .fillMaxSize()
            .padding(top = authContentTransitionDp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(colors.backgroundColors.whiteBackgroundColor)
            .constrainAs(inputFieldsContainer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        VerificationContent(
            state = state,
            listener = listener,
            modifier = authContentModifier
        )
    }
}

/**
 * VERIFICATION CONTENT
 */
@Composable
private fun VerificationContent(
    state: State,
    listener: Listener,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopTitle("Верификация")
        Description()
        VerificationCode()
        Spacer(modifier = Modifier.weight(1f))
        ResendText(state)
        VerifyButton()
    }
}

/**
 * DESCRIPTION
 */
@Composable
private fun Description() = Text(
    text = "Мы отправили код на указанную вами почту. Введите его в поле ниже.",
    color = colors.textColors.blackTextColor,
    style = MaterialTheme.typography.body1,
    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
)

/**
 * CODE FIELDS
 */
@Composable
fun VerificationCode() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        repeat(6) { index ->
            VerificationCodeItem()
            if (index != 5) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

/**
 * INPUT FIELD CODE ITEM
 */
@Composable
fun VerificationCodeItem() {
    val verificationCodeItemWidth by rememberUpdatedState(
        newValue = (getScreenWidth() - 5 * 16.dp) / 6
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(verificationCodeItemWidth)
            .height(3 / 2 * verificationCodeItemWidth)
            .clip(MaterialTheme.shapes.large)
            .border(
                width = 2.dp,
                color = colors.borderColors.lightGrayColor,
                shape = MaterialTheme.shapes.large
            )
    ) {
        AutoSizeText(
            text = "5",
            color = colors.textColors.blackTextColor.copy(
                alpha = 0.75f
            ),
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

/**
 * RESEND TEXT
 */
@Composable
private fun ResendText(state: State) {
    Text(
        text = "Не пришел код?\nСможем прислать ещё один через ${state.timerSeconds} сек",
        style = MaterialTheme.typography.h5,
        color = colors.textColors.lightGrayTextColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

/**
 * VERIFY BUTTON
 */
@Composable
private fun VerifyButton() {
    StateButton(
        text = "Вот вам код",
        state = ButtonState.DEFAULT,
        onClick = {},
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
    )
}