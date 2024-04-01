package ui.mobile.auth

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_login
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.common.auth.AuthContract.Effect.NavigateToPrivacyPolicyInfo
import ui.common.auth.AuthContract.Effect.NavigateToVerification
import ui.common.auth.AuthContract.Event
import ui.common.auth.AuthContract.Listener
import ui.common.auth.AuthContract.State
import ui.common.auth.AuthViewModel

@Composable
internal fun AuthMainScreenMobile(
    viewModel: AuthViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onUserInputChanged(input: String) {
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
        modifier = modifier
    ) {

    }
}

/**
 * INPUT FIELDS CONTAINER
 */
@Composable
private fun InputFieldsContainer(
    state: State,
    listener: Listener
) {

}

/**
 * TITLE
 */
@Composable
private fun Title(state: State) = Text(
    text = "Авторизация",
    color = colors.textColors.blackTextColor
)

/**
 * INPUT FIELDS
 */
@Composable
private fun InputFields(
    state: State,
    listener: Listener
) {

}

@Composable
private fun InputField() {

}

/**
 * AUTH BUTTON
 */
@Composable
private fun Button(
    state: State,
    listener: Listener
) {

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