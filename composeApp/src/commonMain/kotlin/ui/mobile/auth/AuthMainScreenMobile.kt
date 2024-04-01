package ui.mobile.auth

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.common.auth.AuthContract.Effect.NavigateToPrivacyPolicyInfo
import ui.common.auth.AuthContract.Effect.NavigateToVerification
import ui.common.auth.AuthContract.Event
import ui.common.auth.AuthContract.Listener
import ui.common.auth.AuthContract.State
import ui.common.auth.AuthViewModel
import ui.common.auth.data.AuthTextFieldType
import ui.common.auth.data.AuthTextFieldType.EMAIL
import ui.common.auth.data.AuthTextFieldType.PASSWORD
import ui.common.auth.data.AuthTextFieldType.PASSWORD_REPEAT
import ui.common.auth.data.AuthType.SIGN_UP
import ui.core.ButtonState.DEFAULT
import ui.core.CustomTextField
import ui.core.StateButton

@Composable
internal fun AuthMainScreenMobile(
    viewModel: AuthViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onUserInputChanged(type: AuthTextFieldType, input: String) {
            viewModel.setEvent(Event.HandleUserInput)
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
                // TODO
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
//        Spacer(modifier = Modifier.weight(1f))
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
    ) {
        val focusManager = LocalFocusManager.current
        InputField(
            state = state,
            fieldType = EMAIL,
            text = state.email,
            hint = "Почта",
            focusManager = focusManager,
            listener = listener
        )
        InputField(
            state = state,
            fieldType = PASSWORD,
            text = state.password,
            hint = "Пароль",
            focusManager = focusManager,
            listener = listener
        )
        if (state.authMode == SIGN_UP) {
            InputField(
                state = state,
                fieldType = PASSWORD_REPEAT,
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
    fieldType: AuthTextFieldType,
    text: String,
    hint: String,
    focusManager: FocusManager,
    listener: Listener?
) {
    CustomTextField(
        value = text,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = {
            listener?.onUserInputChanged(fieldType, it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        ),
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                border = BorderStroke(
                    width = 1.25.dp,
                    color = colors.borderColors.lightGrayColor
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 3.dp)
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
            imeAction = ImeAction.Next,
//            imeAction = when {
//                state.authType == SIGN_IN && fieldType == PASSWORD -> ImeAction.Done
//                state.authType == SIGN_UP && fieldType == PASSWORD_REPEAT -> ImeAction.Done
//                else -> ImeAction.Next
//            },
            keyboardType = if (fieldType == EMAIL) KeyboardType.Email else KeyboardType.Password
        ),
        trailingIcon = {
            if (fieldType == PASSWORD || fieldType == PASSWORD_REPEAT) {
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
        visualTransformation = if (state.isUserInputHidden && fieldType != EMAIL) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
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
            .padding(top = 32.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
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

}

/**
 * PRIVACY POLICY TEXT
 */
@Composable
private fun PrivacyPolicyInfo(
    state: State,
    listener: Listener
) {

}