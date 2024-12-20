import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.AppContract.Event
import app.AppViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.dialog.AppDialog
import nowiwr01p.daily.doctor.app_presentation.dialogs.getDialogContent
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.MobileNavigator
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import screens.getScreenContent
import theme.AppTheme
import view_model.rememberViewModel

@Composable
fun App(
    context: ComponentContext,
    viewModel: AppViewModel = rememberViewModel(),
    navigator: MobileNavigator = koinInject { parametersOf(context) }
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    AppTheme(
        appColorTheme = viewModel.viewState.value.appColorTheme
    ) {
        AppContent(navigator)
    }
}

@Composable
private fun AppContent(navigator: MobileNavigator) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Screens(navigator)
        BottomSheets(navigator)
        Dialogs(navigator)
    }
}

@Composable
private fun Screens(navigator: MobileNavigator) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = navigator.screensNavigator.screensChildStack,
        animation = stackAnimation { child -> child.instance.animation },
        content = { child -> child.instance.getScreenContent(navigator) }
    )
}

@Composable
private fun Dialogs(navigator: MobileNavigator) {
    val isDialogShown = remember { mutableStateOf(false) }
    navigator.dialogsNavigator.hideDialogCallback = {
        isDialogShown.value = false
    }
    val dialogSlot by navigator.dialogsNavigator.dialogsChildSlot.subscribeAsState()
    val dialogChild = dialogSlot.child?.instance
    dialogChild?.let { child ->
        val onDismissCallback = { navigator.dialogsNavigator.navigateBack() }
        AppDialog(
            isDialogShown = isDialogShown,
            isCancellable = child.isCancellable,
            onDismissRequest = onDismissCallback,
            content = { child.getDialogContent(onDismiss = onDismissCallback) }
        )
    }
}

@Composable
private fun BottomSheets(navigator: MobileNavigator) {

}
