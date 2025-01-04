package screens.auth

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import auth.AuthViewModel
import auth.Effect.NavigateToPin
import auth.Effect.NavigateToPrivacyPolicyInfo
import auth.Effect.NavigateToVerification
import auth.Event.HandleUserInput
import auth.Event.OnAuthClicked
import auth.Event.OnPrivacyPolicyClicked
import auth.Event.ToggleAuthMode
import auth.Event.ToggleUserInputVisibility
import auth.Listener
import auth.State
import auth.data.AuthType.SIGN_IN
import auth.data.AuthType.SIGN_UP
import components.button.AppButton
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.input_field.CustomTextField
import extensions.BaseScreen
import extensions.LinkTag
import extensions.buildAnnotatedStringFromStringWithTags
import extensions.isKeyboardOpened
import extensions.onTextClick
import model.errors.auth.AuthTextFieldType
import model.errors.auth.AuthTextFieldType.PASSWORD
import model.errors.auth.AuthTextFieldType.PASSWORD_CONFIRMATION
import model.errors.auth.AuthTextFieldType.PHONE
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileScreensChild.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode.Check
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode.Create
import com.nowiwr01p.model.resources.component_with_resources.screens.auth.AuthScreenResources
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_eye_closed
import nowiwr01p.daily.doctor.resources.ic_eye_opened
import nowiwr01p.daily.doctor.resources.ic_login
import org.jetbrains.compose.resources.painterResource
import theme.CustomTheme
import theme.CustomTheme.colors
import view_model.rememberViewModel

@Composable
internal fun AuthChild.AuthMainScreenMobile(
    navigator: MobileNavigator,
    resources: AuthScreenResources,
    viewModel: AuthViewModel = baseComponent.rememberViewModel()
) {
    val listener = object : Listener {
        override fun onUserInputChanged(type: AuthTextFieldType, value: String) {
            viewModel.setEvent(HandleUserInput(type, value))
        }
        override fun onToggleUserInputVisibilityClicked() {
            viewModel.setEvent(ToggleUserInputVisibility)
        }
        override fun onToggleAuthModeClicked() {
            viewModel.setEvent(ToggleAuthMode)
        }
        override fun onAuthClicked() {
            viewModel.setEvent(OnAuthClicked)
        }
        override fun onPrivacyPolicyClicked() {
            viewModel.setEvent(OnPrivacyPolicyClicked)
        }
    }
    val state = viewModel.getState { effect ->
        when (effect) {
            is NavigateToPin -> {
                val pinCodeMode = if (effect.isPinCodeSet) Check(effect.token) else Create(effect.token) // TODO: Do it in ViewModel
                navigator.screensNavigator.pinCodeNavigator.navigateToPinCode(pinCodeMode)
            }
            is NavigateToVerification -> {
                navigator.screensNavigator.authNavigator.navigateToVerification(effect.phone, effect.token)
            }
            is NavigateToPrivacyPolicyInfo -> {
                // TODO
            }
        }
    }
    BaseScreen(
        topBackgroundColor = colors.backgroundColors.grayBackgroundColor,
        bottomBackgroundColor = colors.backgroundColors.whiteBackgroundColor
    ) {
        resources.AuthMainScreenContent(state, listener)
    }
}

/**
 * CONTENT
 */
@Composable
private fun AuthScreenResources.AuthMainScreenContent(
    state: State,
    listener: Listener
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.grayBackgroundColor)
    ) {
        val (icon, inputFieldsContainer) = createRefs()

        val isKeyboardOpen by isKeyboardOpened()
        val authContentTransitionDp by animateDpAsState(
            targetValue = if (isKeyboardOpen) 8.dp else 160.dp,
            animationSpec = tween(durationMillis = 150, easing = LinearEasing)
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
            .fillMaxWidth()
            .padding(top = authContentTransitionDp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(colors.backgroundColors.whiteBackgroundColor)
            .constrainAs(inputFieldsContainer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        AuthContent(
            state = state,
            listener = listener,
            modifier = authContentModifier
        )
    }
}

/**
 * TOP ICON
 */
@Composable
internal fun TopIcon(modifier: Modifier) = Image(
    painter = painterResource(Res.drawable.ic_login),
    contentDescription = "",
    modifier = modifier
)

/**
 * AUTH CONTENT
 */
@Composable
private fun AuthScreenResources.AuthContent(
    state: State,
    listener: Listener,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopTitle(authTitle)
        InputFields(state, listener)
        AuthButton(state, listener)
        AlreadyHaveAnAccountText(state, listener)
        Spacer(modifier = Modifier.weight(1f))
        PrivacyPolicyInfo(state, listener)
    }
}

/**
 * TITLE
 */
@Composable
internal fun TopTitle(text: String) = Text(
    text = text,
    color = colors.textColors.blackTextColor,
    style = CustomTheme.typography.displayMedium,
    modifier = Modifier.padding(top = 32.dp)
)

/**
 * INPUT FIELDS
 */
@Composable
private fun AuthScreenResources.InputFields(
    state: State,
    listener: Listener
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .animateContentSize(
                animationSpec = tween()
            )
    ) {
        val focusManager = LocalFocusManager.current
        InputField(
            state = state,
            type = PHONE,
            text = state.phone,
            hint = authInputPhoneHint,
            focusManager = focusManager,
            listener = listener
        )
        InputField(
            state = state,
            type = PASSWORD,
            text = state.password,
            hint = authInputPasswordHint,
            focusManager = focusManager,
            listener = listener
        )
        if (state.authMode == SIGN_UP) {
            InputField(
                state = state,
                type = PASSWORD_CONFIRMATION,
                text = state.passwordConfirmation,
                hint = authInputPasswordRepeatHint,
                focusManager = focusManager,
                listener = listener
            )
        }
    }
}

@Composable
private fun InputField(
    state: State,
    type: AuthTextFieldType,
    text: String,
    hint: String,
    focusManager: FocusManager,
    listener: Listener?
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colors.textColors.redTextColor,
        backgroundColor = colors.textColors.redTextColor.copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        CustomTextField(
            value = text,
            textStyle = CustomTheme.typography.bodyLarge,
            onValueChange = {
                listener?.onUserInputChanged(type, it)
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = colors.textColors.redTextColor,
                backgroundColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    border = BorderStroke(
                        width = 1.25.dp,
                        color = if (state.authError != null && state.authError!!.list.contains(type)) {
                            colors.borderColors.redColor
                        } else {
                            colors.borderColors.lightGrayColor
                        }
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            placeholder = {
                Text(
                    text = hint,
                    style = CustomTheme.typography.bodyLarge
                )
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onDone = {
                    listener?.onAuthClicked()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = when {
                    state.authMode == SIGN_IN && type == PASSWORD -> ImeAction.Done
                    state.authMode == SIGN_UP && type == PASSWORD_CONFIRMATION -> ImeAction.Done
                    else -> ImeAction.Next
                },
                keyboardType = if (type == PHONE) KeyboardType.Phone else KeyboardType.Password
            ),
            trailingIcon = {
                if (type == PASSWORD || type == PASSWORD_CONFIRMATION) {
                    val icon = when {
                        state.isUserInputHidden -> Res.drawable.ic_eye_closed
                        else -> Res.drawable.ic_eye_opened
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                listener?.onToggleUserInputVisibilityClicked()
                            }
                    ) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = "Show or hide password icon",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            },
            visualTransformation = if (state.isUserInputHidden && type != PHONE) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            contentPadding = PaddingValues(start = 12.dp, top = 19.dp)
        )
    }
}

/**
 * AUTH BUTTON
 */
@Composable
private fun AuthScreenResources.AuthButton(
    state: State,
    listener: Listener
) {
    val focusManager = LocalFocusManager.current
    AppButton(
        text = when (state.authMode) {
            SIGN_IN -> authButtonSignInTitle
            SIGN_UP -> authButtonSignUpTitle
        },
        state = state.buttonState,
        enabled = state.buttonState == DARK_GRAY_ACTIVE,
        onClick = {
            listener.onAuthClicked()
            focusManager.clearFocus()
        },
        modifier = Modifier
            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(24.dp))
    )
}

/**
 * ALREADY HAVE AN ACCOUNT TOGGLE TEXT
 */
@Composable
private fun AuthScreenResources.AlreadyHaveAnAccountText(
    state: State,
    listener: Listener
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .padding(top = 32.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                if (state.authMode == SIGN_IN) {
                    keyboard?.hide()
                }
                listener.onToggleAuthModeClicked()
            }
    ) {
        Text(
            text = when (state.authMode) {
                SIGN_IN -> authButtonHaveNoAccountYetTitle
                SIGN_UP -> authButtonAlreadyHaveAccountTitle
            },
            style = CustomTheme.typography.headlineMedium,
            color = colors.textColors.lightGrayTextColor,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

/**
 * PRIVACY POLICY TEXT
 */
@Composable
private fun AuthScreenResources.PrivacyPolicyInfo(
    state: State,
    listener: Listener
) {
    val annotatedString = buildAnnotatedStringFromStringWithTags(fullText = authAgreeWithPoliciesTitle)
    ClickableText(
        text = annotatedString,
        style = CustomTheme.typography.headlineSmall.copy(
            color = colors.textColors.lightGrayTextColor,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
        onClick = { offset ->
            annotatedString.onTextClick(offset) { tag ->
                when (tag) {
                    LinkTag.PrivacyPolicy.tag -> {
                        // TODO
                    }
                    LinkTag.TermsAndConditions.tag -> {
                        // TODO
                    }
                }
            }
        }
    )
}
