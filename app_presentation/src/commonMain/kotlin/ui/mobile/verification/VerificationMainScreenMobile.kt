package ui.mobile.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import navigation.MainNavigator
import platform.getScreenWidth
import ui.common.verification.VerificationContract.Effect
import ui.common.verification.VerificationContract.Event
import ui.common.verification.VerificationContract.Listener
import ui.common.verification.VerificationContract.State
import ui.common.verification.VerificationViewModel
import ui.common.verification.data.VerificationEnterCodeOperation
import ui.common.verification.data.VerificationEnterCodeOperation.RemoveDigit
import ui.common.verification.data.VerificationEnterCodeOperation.SetDigit
import ui.core_ui.components.button.StateButton
import ui.core_ui.components.input_field.CustomTextField
import ui.mobile.auth.TopIcon
import ui.mobile.auth.TopTitle

@Composable
internal fun VerificationMainScreenMobile(
    navigator: MainNavigator,
    viewModel: VerificationViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onVerifyClicked() {
            viewModel.setEvent(Event.OnVerifyClicked)
        }
        override fun onResendCodeClicked() {
            viewModel.setEvent(Event.OnResendCodeClicked)
        }
        override fun handeUserInput(operation: VerificationEnterCodeOperation) {
            viewModel.setEvent(Event.HandeUserInput(operation))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is Effect.NavigateToHome -> {
                // TODO
            }
        }
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
            .padding(top = 160.dp)
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
        modifier = modifier.imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopTitle("Верификация")
        Description()
        VerificationCode(state, listener)
        Spacer(modifier = Modifier.weight(1f))
        ResendText(state, listener)
        VerifyButton(state, listener)
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
private fun VerificationCode(
    state: State,
    listener: Listener
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        repeat(state.code.size) { index ->
            VerificationCodeItem(
                index = index,
                state = state,
                listener = listener
            )
            if (index != state.code.lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

/**
 * INPUT FIELD CODE ITEM
 */
@Composable
private fun VerificationCodeItem(
    index: Int,
    state: State,
    listener: Listener
) {
    val verificationCodeItemSize by rememberUpdatedState(
        newValue = (getScreenWidth() - 5 * 16.dp) / 6
    )
    val verticalInputPadding by rememberUpdatedState(
        (verificationCodeItemSize - 28.dp) / 2
    )
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colors.backgroundColors.redBackgroundColor,
        backgroundColor = colors.backgroundColors.redBackgroundColor.copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        val focusManager = LocalFocusManager.current
        var enteredDigit by remember { mutableStateOf("") }
        CustomTextField(
            value = if (index <= state.code.lastIndex) state.code[index] else "",
            contentPadding = PaddingValues(top = verticalInputPadding),
            onValueChange = { digit ->
                enteredDigit = digit.lastOrNull()?.toString() ?: ""
                if (enteredDigit.isNotEmpty()) {
                    listener.handeUserInput(SetDigit(index, enteredDigit))
                    with(focusManager) {
                        if (index == state.code.lastIndex) clearFocus() else moveFocus(FocusDirection.Next)
                    }
                }
            },
            textStyle = MaterialTheme.typography.h3.copy(
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = colors.textColors.blackTextColor.copy(alpha = 0.75f)
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.textColors.blackTextColor,
                cursorColor = colors.backgroundColors.redBackgroundColor,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = when {
                    index == state.code.lastIndex && enteredDigit.isNotEmpty() -> ImeAction.Done
                    else -> ImeAction.Next
                }
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (enteredDigit.isNotEmpty()) {
                        focusManager.clearFocus()
                    }
                },
                onNext = {
                    if (enteredDigit.isNotEmpty()) {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                }
            ),
            modifier = Modifier
                .size(verificationCodeItemSize)
                .clip(MaterialTheme.shapes.large)
                .border(
                    width = 2.dp,
                    color = colors.borderColors.lightGrayColor,
                    shape = MaterialTheme.shapes.large
                )
                .onKeyEvent { keyEvent ->
                    when (keyEvent.key) {
                        Key.Backspace -> {
                            listener.handeUserInput(RemoveDigit(index))
                            if (index != 0) {
                                focusManager.moveFocus(FocusDirection.Previous)
                            } else {
                                false
                            }
                        }
                        else -> false
                    }
                }
        )
    }
}

/**
 * RESEND TEXT
 */
@Composable
private fun ResendText(
    state: State,
    listener: Listener
) {
    Text(
        text = when {
            state.timerSeconds == 0 -> "Мне нужен новый код"
            else -> "Не пришёл код?\nОтправим ещё один через ${state.timerSeconds} сек"
        },
        style = MaterialTheme.typography.h5,
        color = colors.textColors.lightGrayTextColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .wrapContentSize()
            .clip(MaterialTheme.shapes.large)
            .clickable(enabled = state.timerSeconds == 0) {
                listener.onResendCodeClicked()
            }
            .padding(vertical = 4.dp, horizontal = 8.dp)
    )
}

/**
 * VERIFY BUTTON
 */
@Composable
private fun VerifyButton(
    state: State,
    listener: Listener
) {
    StateButton(
        text = "Подтвердить",
        state = state.buttonState,
        onClick = { listener.onVerifyClicked() },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
    )
}