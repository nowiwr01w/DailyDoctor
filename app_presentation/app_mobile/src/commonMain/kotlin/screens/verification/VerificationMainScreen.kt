package screens.verification

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.button.AppButton
import extensions.BaseScreen
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.VerificationChild
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification.VerificationScreenResources
import screens.auth.TopIcon
import screens.auth.TopTitle
import theme.CustomTheme.colors
import theme.CustomTheme.shapes
import theme.CustomTheme.typography
import verification.Effect.NavigateToPinCode
import verification.Effect.OpenKeyboardToEnterCorrectCode
import verification.Event.OnResendCodeClicked
import verification.Event.VerificationCodeInputChanged
import verification.Listener
import verification.State
import verification.VERIFICATION_CODE_ANIMATION_DURATION
import verification.VERIFICATION_CODE_LENGTH
import verification.VerificationViewModel
import view_model.rememberViewModel

@Composable
internal fun VerificationChild.VerificationMainScreenMobile(
    navigator: MobileNavigator,
    resources: VerificationScreenResources,
    viewModel: VerificationViewModel = baseComponent.rememberViewModel(phone, verificationToken, resources)
) {
    val focusRequester = remember {
        FocusRequester()
    }
    val listener = object : Listener {
        override fun onResendCodeClicked() {
            viewModel.setEvent(OnResendCodeClicked)
        }
        override fun onVerificationCodeInputChange(code: String) {
            viewModel.setEvent(VerificationCodeInputChanged(code))
        }
    }
    val state = viewModel.getState { effect ->
        when (effect) {
            is NavigateToPinCode -> {
                val pinCodeCreateMode = PinCodeMode.Create(effect.pinCodeToken)
                navigator.screensNavigator.pinCodeNavigator.navigateToPinCode(pinCodeCreateMode)
            }
            is OpenKeyboardToEnterCorrectCode -> {
                focusRequester.requestFocus()
            }
        }
    }
    BaseScreen(
        topBackgroundColor = colors.backgroundColors.grayBackgroundColor,
        bottomBackgroundColor = colors.backgroundColors.whiteBackgroundColor,
    ) {
        resources.VerificationMainScreenContent(
            state = state,
            listener = listener,
            focusRequester = focusRequester
        )
    }
}

/**
 * CONTENT
 */
@Composable
private fun VerificationScreenResources.VerificationMainScreenContent(
    state: State,
    listener: Listener,
    focusRequester: FocusRequester
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
            focusRequester = focusRequester,
            modifier = authContentModifier
        )
    }
}

/**
 * VERIFICATION CONTENT
 */
@Composable
private fun VerificationScreenResources.VerificationContent(
    state: State,
    listener: Listener,
    focusRequester: FocusRequester,
    modifier: Modifier
) {
    Column(
        modifier = modifier.imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopTitle(text = verificationTitle)
        Description()
        VerificationCode(
            state = state,
            listener = listener,
            focusRequester = focusRequester
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        ResendText(
            state = state,
            listener = listener
        )
        VerifyButton(state)
    }
}

/**
 * DESCRIPTION
 */
@Composable
private fun VerificationScreenResources.Description() = Text(
    text = verificationCodeSentDescription,
    color = colors.textColors.blackTextColor,
    style = typography.bodyLarge,
    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
)

/**
 * VERIFICATION CODE
 */
@Composable
fun VerificationCode(
    state: State,
    listener: Listener?,
    focusRequester: FocusRequester
) {
    val initialCode = state.code
    var enteredCode by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val codeTextFieldValue = remember(initialCode) {
        val value = TextFieldValue(initialCode, TextRange(initialCode.length))
        mutableStateOf(value)
    }

    LaunchedEffect(enteredCode) {
        listener?.onVerificationCodeInputChange(enteredCode)
        if (enteredCode.length == VERIFICATION_CODE_LENGTH) {
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = Modifier.animateContentSize(
            animationSpec = tween(durationMillis = VERIFICATION_CODE_ANIMATION_DURATION)
        )
    ) {
        BasicTextField(
            value = codeTextFieldValue.value,
            onValueChange = { textFieldValue ->
                enteredCode = textFieldValue.text
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            decorationBox = {
                CodeInputDecorationBox(
                    code = codeTextFieldValue.value.text,
                    isError = state.errorText != null
                )
            },
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 32.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
        state.errorText?.let { errorText ->
            Text(
                text = errorText,
                color = Color(0xFFE34446),
                style = typography.labelMedium,
                modifier = Modifier
                    .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun CodeInputDecorationBox(
    code: String,
    isError: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(VERIFICATION_CODE_LENGTH) { index ->
            CodeEntry(
                digit = if (index < code.length) code[index].toString() else "",
                isError = isError
            )
        }
    }
}

@Composable
private fun RowScope.CodeEntry(
    digit: String,
    isError: Boolean
) {
    val borderColor by animateColorAsState(
        targetValue = if (isError) colors.borderColors.redColor else colors.borderColors.lightGrayColor,
        animationSpec = tween(durationMillis = VERIFICATION_CODE_ANIMATION_DURATION)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(56.dp)
            .weight(1f)
            .background(
                color = colors.backgroundColors.whiteBackgroundColor,
                shape = shapes.medium
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shapes.medium
            )
    ) {
        androidx.compose.animation.AnimatedVisibility(
            visible = digit.isNotEmpty(),
            enter = fadeIn(animationSpec = tween(durationMillis = VERIFICATION_CODE_ANIMATION_DURATION)),
            exit = fadeOut(animationSpec = tween(durationMillis = VERIFICATION_CODE_ANIMATION_DURATION))
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = colors.backgroundColors.grayBackgroundColor,
                        shape = CircleShape
                    )
            )
        }
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
        text = state.resendTimerState.text,
        style = typography.headlineMedium,
        color = colors.textColors.lightGrayTextColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .wrapContentSize()
            .clip(shapes.large)
            .clickable(enabled = state.resendTimerState.isClickEnabled) {
                listener.onResendCodeClicked()
            }
            .padding(vertical = 4.dp, horizontal = 8.dp)
    )
}

/**
 * VERIFY BUTTON
 */
@Composable
private fun VerificationScreenResources.VerifyButton(state: State) {
    AppButton(
        text = confirm,
        state = state.buttonState,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
    )
}
