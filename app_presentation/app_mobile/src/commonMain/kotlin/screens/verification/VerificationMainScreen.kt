package screens.verification

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
import theme.CustomTheme
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
import observers.EffectObserver
import view_model.rememberViewModel
import theme.CustomTheme.colors
import verification.VerificationContract.Effect
import verification.VerificationContract.Event
import verification.VerificationContract.Listener
import verification.VerificationContract.State
import verification.VerificationViewModel
import verification.data.VerificationEnterCodeOperation
import verification.data.VerificationEnterCodeOperation.RemoveDigit
import verification.data.VerificationEnterCodeOperation.SetDigit
import components.button.StateButton
import components.input_field.CustomTextField
import extensions.BaseScreen
import getScreenWidth
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.VerificationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.verification_code_sent_description
import nowiwr01p.daily.doctor.resources.verification_new_code_required
import nowiwr01p.daily.doctor.resources.verification_send_code_again
import nowiwr01p.daily.doctor.resources.verification_title
import org.jetbrains.compose.resources.stringResource
import screens.auth.TopIcon
import screens.auth.TopTitle

@Composable
internal fun VerificationChild.VerificationMainScreenMobile(
    navigator: MobileNavigator,
    viewModel: VerificationViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onVerifyClicked() {
            viewModel.setEvent(Event.OnVerifyClicked(phone, verificationToken))
        }
        override fun onResendCodeClicked() {
            viewModel.setEvent(Event.OnResendCodeClicked(phone))
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
            is Effect.NavigateToPinCode -> {
                val pinCodeCreateMode = PinCodeMode.Create(effect.pinCodeToken)
               navigator.screensNavigator.pinCodeNavigator.navigateToPinCode(pinCodeCreateMode)
            }
        }
    }

    BaseScreen(
        topBackgroundColor = colors.backgroundColors.grayBackgroundColor,
        bottomBackgroundColor = colors.backgroundColors.whiteBackgroundColor,
    ) {
        VerificationMainScreenContent(
            state = viewModel.viewState.value,
            listener = listener
        )
    }
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
        TopTitle(text = Res.string.verification_title)
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
    text = stringResource(Res.string.verification_code_sent_description),
    color = colors.textColors.blackTextColor,
    style = CustomTheme.typography.bodyLarge,
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
        newValue = (verificationCodeItemSize - 28.dp) / 2
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
            textStyle = CustomTheme.typography.displaySmall.copy(
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
                .clip(CustomTheme.shapes.large)
                .border(
                    width = 2.dp,
                    color = colors.borderColors.lightGrayColor,
                    shape = CustomTheme.shapes.large
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
    val text = when {
        state.timerSeconds == 0L -> stringResource(Res.string.verification_new_code_required)
        else -> {
            stringResource(Res.string.verification_send_code_again, state.timerSeconds.toString())
        }
    }
    Text(
        text = text,
        style = CustomTheme.typography.headlineMedium,
        color = colors.textColors.lightGrayTextColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .wrapContentSize()
            .clip(CustomTheme.shapes.large)
            .clickable(enabled = state.timerSeconds == 0L) {
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
            .clip(RoundedCornerShape(24.dp))
    )
}
