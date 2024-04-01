package ui.mobile.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import base.theme.CustomTheme.colors
import base.view_model.EffectObserver
import base.view_model.rememberViewModel
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_pin
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Icon()
    }
}

/**
 * TOP ICON
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Icon() = Image(
    painter = painterResource(Res.drawable.ic_pin),
    contentDescription = "",
    modifier = Modifier
        .padding(top = 16.dp)
        .size(96.dp)
)

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