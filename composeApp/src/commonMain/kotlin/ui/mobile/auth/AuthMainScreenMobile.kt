package ui.mobile.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import base.view_model.rememberViewModel
import ui.common.auth.AuthContract.Listener
import ui.common.auth.AuthContract.State
import ui.common.auth.AuthViewModel

@Composable
internal fun AuthMainScreenMobile(
    viewModel: AuthViewModel = rememberViewModel()
) {
    val listener = object : Listener {

    }
    AuthMainScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

@Composable
private fun AuthMainScreenContent(
    state: State,
    listener: Listener
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}