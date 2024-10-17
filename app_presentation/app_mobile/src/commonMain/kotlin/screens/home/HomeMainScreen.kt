package screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import home.HomeContract.Listener
import home.HomeContract.State
import home.HomeViewModel
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import view_model.rememberViewModel

@Composable
fun HomeMainScreen(
    navigator: MainNavigator,
    viewModel: HomeViewModel = rememberViewModel()
) {
    val listener = object : Listener {

    }

    Content(
        state = viewModel.viewState.value,
        listener = listener
    )
}

@Composable
private fun Content(
    state: State,
    listener: Listener?
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
}