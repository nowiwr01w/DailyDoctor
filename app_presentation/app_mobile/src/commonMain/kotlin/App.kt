import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.AppContract.Event
import app.AppViewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import components.bottom_sheet.BottomSheet
import components.bottom_sheet.ShowBottomSheetHelper
import components.snack_bar.SnackBar
import helpers.snack_bar.SnackBarHelper
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
    snackBarHelper: SnackBarHelper = koinInject(),
    showBottomSheetHelper: ShowBottomSheetHelper = koinInject(),
    navigator: MobileNavigator = koinInject { parametersOf(context) }
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = Hidden,
        skipHalfExpanded = true
    )

    showBottomSheetHelper.init(scope, bottomSheetState)

    LaunchedEffect(Unit) {
        snapshotFlow { bottomSheetState.currentValue }
            .collect {
                if (it == Hidden) showBottomSheetHelper.closeBottomSheet()
            }
    }

    val appState = viewModel.viewState.value
    AppTheme(
        appColorTheme = appState.appColorTheme
    ) {
        val subscribeOnBottomSheet: @Composable () -> Unit = {
            showBottomSheetHelper.content.collectAsState(null).value?.let { params ->
                BottomSheet(params)
            }
            Spacer(modifier = Modifier.height(1.dp))
        }

        val subscribeOnSnackBar: @Composable () -> Unit = {
            val snackBarParams by snackBarHelper.params.collectAsState()
            val transition = updateTransition(snackBarParams)
            SnackBar(transition)
        }

        AppContent(
            navigator = navigator,
            bottomSheetState = bottomSheetState,
            subscribeOnSnackBar = subscribeOnSnackBar,
            subscribeOnBottomSheet = subscribeOnBottomSheet
        )
    }
}

@Composable
private fun AppContent(
    navigator: MobileNavigator,
    bottomSheetState: ModalBottomSheetState,
    subscribeOnSnackBar: @Composable () -> Unit,
    subscribeOnBottomSheet: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetGesturesEnabled = false,
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = { subscribeOnBottomSheet() }
    ) {
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = navigator.screensNavigator.screensChildStack,
            animation = stackAnimation { child -> child.instance.animation },
            content = { child -> child.instance.getScreenContent(navigator) }
        )
    }
    subscribeOnSnackBar()
}
