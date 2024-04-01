package ui.mobile.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import base.view_model.rememberViewModel
import ui.common.verification.VerificationContract.Listener
import ui.common.verification.VerificationContract.State
import ui.common.verification.VerificationViewModel

@Composable
internal fun VerificationMainScreenMobile(
    viewModel: VerificationViewModel = rememberViewModel()
) {
    val listener = object : Listener {

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
    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}