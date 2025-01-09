package screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import home.HomeViewModel
import home.Listener
import home.State
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.MobileNavigator
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.child.MobileScreensChild.HomeChild
import view_model.rememberViewModel

@Composable
fun HomeChild.HomeMainScreen(
    navigator: MobileNavigator,
    viewModel: HomeViewModel = baseComponent.rememberViewModel()
) {
    val listener = object : Listener {

    }
    val state = viewModel.getState {

    }
    Content(
        state = state,
        listener = listener
    )
}

@Composable
private fun Content(
    state: State,
    listener: Listener?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}
