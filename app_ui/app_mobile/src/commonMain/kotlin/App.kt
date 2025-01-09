import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.AppViewModel
import app.State
import bottom_sheets.getBottomSheetContent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.transition_component.AppBottomSheet
import components.transition_component.AppDialog
import nowiwr01p.daily.doctor.app_ui.dialogs.getDialogContent
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.MobileNavigator
import com.nowiwr01p.model.resources.language.AppWithLanguage
import components.snack_bar.SnackBar
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import screens.getScreenContent
import theme.AppTheme
import view_model.rememberViewModel

@Composable
fun App(
    context: ComponentContext,
    globalInstanceKeeperOwner: GlobalInstanceKeeperOwner,
    viewModel: AppViewModel = globalInstanceKeeperOwner.rememberViewModel(),
    navigator: MobileNavigator = koinInject { parametersOf(context) }
) {
    val appState = viewModel.getState { effect ->
        // TODO
    }
    AppWithLanguage(
        appLanguage = appState.appLanguage
    ) {
        AppTheme(appColorTheme = appState.appColorTheme) {
            AppContent(
                appState = appState,
                navigator = navigator
            )
        }
    }
}

@Composable
private fun AppContent(
    appState: State,
    navigator: MobileNavigator
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Screens(navigator)
        BottomSheets(navigator)
        Dialogs(navigator)
        SnackBars(appState)
    }
}

/**
 * SCREENS
 */
@Composable
private fun Screens(navigator: MobileNavigator) {
    Children(
        modifier = Modifier.fillMaxSize(),
        stack = navigator.screensNavigator.screensChildStack,
        animation = stackAnimation { child -> child.instance.animation },
        content = { child -> child.instance.getScreenContent(navigator) }
    )
}

/**
 * DIALOGS
 */
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

/**
 * BOTTOM SHEETS
 */
@Composable
private fun BottomSheets(navigator: MobileNavigator) {
    val isBottomSheetShown = remember { mutableStateOf(false) }
    navigator.bottomSheetsNavigator.hideBottomSheetCallback = {
        isBottomSheetShown.value = false
    }
    val bottomSheetSlot by navigator.bottomSheetsNavigator.bottomSheetsChildSlot.subscribeAsState()
    val bottomSheetChild = bottomSheetSlot.child?.instance
    bottomSheetChild?.let { child ->
        val onDismissCallback = { navigator.bottomSheetsNavigator.navigateBack() }
        AppBottomSheet(
            isBottomSheetShown = isBottomSheetShown,
            isCancellable = child.isCancellable,
            onDismissRequest = onDismissCallback,
            content = { child.getBottomSheetContent(navigator) }
        )
    }
}

/**
 * SNACK BARS
 */
@Composable
private fun SnackBars(state: State) {
    val transition = updateTransition(state.snackBarParams)
    SnackBar(transition)
}