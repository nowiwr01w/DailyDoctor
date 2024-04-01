package ui.mobile.auth

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_eye_closed
import dailydoctor.composeapp.generated.resources.ic_eye_opened
import dailydoctor.composeapp.generated.resources.ic_login
import domain.repository.auth.data.errors.AuthTextFieldType
import domain.repository.auth.data.errors.AuthTextFieldType.EMAIL
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD_CONFIRMATION
import navigation.MainNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.common.auth.AuthContract.Effect.NavigateToPrivacyPolicyInfo
import ui.common.auth.AuthContract.Effect.NavigateToVerification
import ui.common.auth.AuthContract.Event
import ui.common.auth.AuthContract.Listener
import ui.common.auth.AuthContract.State
import ui.common.auth.AuthViewModel
import ui.common.auth.data.AuthType.SIGN_IN
import ui.common.auth.data.AuthType.SIGN_UP
import ui.core_ui.components.ButtonState.DEFAULT
import ui.core_ui.components.CustomTextField
import ui.core_ui.components.StateButton
import ui.core_ui.extensions.appendLink
import ui.core_ui.extensions.onTextClick

@Composable
internal fun AuthMainScreenMobile(
    navigator: MainNavigator,
    viewModel: AuthViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onUserInputChanged(type: AuthTextFieldType, value: String) {
            viewModel.setEvent(Event.HandleUserInput(type, value))
        }
        override fun onToggleUserInputVisibilityClicked() {
            viewModel.setEvent(Event.ToggleUserInputVisibility)
        }
        override fun onToggleAuthModeClicked() {
            viewModel.setEvent(Event.ToggleAuthMode)
        }
        override fun onAuthClicked() {
            viewModel.setEvent(Event.OnAuthClicked)
        }
        override fun onPrivacyPolicyClicked() {
            viewModel.setEvent(Event.OnPrivacyPolicyClicked)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    EffectObserver(viewModel.effect) { effect ->
        when (effect) {
            is NavigateToVerification -> {
                navigator.authNavigator.navigateToVerification()
            }
            is NavigateToPrivacyPolicyInfo -> {
                // TODO
            }
        }
    }

    AuthMainScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

/**
 * CONTENT
 */
@Composable
private fun AuthMainScreenContent(
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
        Icon(modifier = iconModifier)

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
@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Icon(modifier: Modifier) = Image(
    painter = painterResource(Res.drawable.ic_login),
    contentDescription = "",
    modifier = modifier
)

/**
 * AUTH CONTENT
 */
@Composable
private fun AuthContent(
    state: State,
    listener: Listener,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTitle(state)
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
private fun AuthTitle(state: State) = Text(
    text = "Авторизация",
    color = colors.textColors.blackTextColor,
    style = MaterialTheme.typography.h2,
    modifier = Modifier.padding(top = 32.dp)
)

/**
 * INPUT FIELDS
 */
@Composable
private fun InputFields(
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
            type = EMAIL,
            text = state.email,
            hint = "Почта",
            focusManager = focusManager,
            listener = listener
        )
        InputField(
            state = state,
            type = PASSWORD,
            text = state.password,
            hint = "Пароль",
            focusManager = focusManager,
            listener = listener
        )
        if (state.authMode == SIGN_UP) {
            InputField(
                state = state,
                type = PASSWORD_CONFIRMATION,
                text = state.passwordConfirmation,
                hint = "Подтверждения пароля",
                focusManager = focusManager,
                listener = listener
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
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
            textStyle = MaterialTheme.typography.body1,
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
                        color = if (state.authError != null && state.authError.list.contains(type)) {
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
                    style = MaterialTheme.typography.body1
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
                keyboardType = if (type == EMAIL) KeyboardType.Email else KeyboardType.Password
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
            visualTransformation = if (state.isUserInputHidden && type != EMAIL) {
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
private fun AuthButton(
    state: State,
    listener: Listener
) {
    StateButton(
        text = if (state.authMode == SIGN_UP) "Зарегистрироваться" else "Войти",
        state = state.buttonState,
        enabled = state.buttonState == DEFAULT,
        onClick = { listener.onAuthClicked() },
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
private fun AlreadyHaveAnAccountText(
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
            text = if (state.authMode == SIGN_IN) "Ещё нет аккаунта" else "Уже есть аккаунт",
            style = MaterialTheme.typography.h5,
            color = colors.textColors.lightGrayTextColor,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

/**
 * PRIVACY POLICY TEXT
 */
@Composable
private fun PrivacyPolicyInfo(
    state: State,
    listener: Listener
) {
    val privacyText = "политикой конфиденциальности"
    val text = buildAnnotatedString {
        append("Продолжая, вы соглашаетесь с нашей ")
        appendLink(privacyText)
    }
    ClickableText(
        text = text,
        style = MaterialTheme.typography.h6.copy(
            color = colors.textColors.lightGrayTextColor,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
        onClick = { offset ->
            text.onTextClick(privacyText, offset) {
//                listener?.openLink(PRIVACY_LINK)
            }
        }
    )
}