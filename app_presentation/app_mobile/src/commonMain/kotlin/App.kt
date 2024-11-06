import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
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
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.AuthChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.HomeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.OnboardingChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.PinCodeChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.SplashChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.SubscriptionChild
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigator.Child.VerificationChild
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import screens.auth.AuthMainScreenMobile
import screens.home.HomeMainScreen
import screens.onboarding.OnboardingMainScreenMobile
import screens.pin_code.PinCodeMainScreenMobile
import screens.splash.SplashMainScreenMobile
import screens.subscription.SubscriptionMainScreen
import screens.verification.VerificationMainScreenMobile
import theme.AppTheme
import view_model.rememberViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(
    context: ComponentContext,
    viewModel: AppViewModel = rememberViewModel(),
    snackBarHelper: SnackBarHelper = koinInject(),
    showBottomSheetHelper: ShowBottomSheetHelper = koinInject(),
    navigator: MainNavigator = koinInject { parametersOf(context) }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppContent(
    navigator: MainNavigator,
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
            stack = navigator.stack,
            animation = stackAnimation { child -> child.instance.animation }
        ) {
            when (val child = it.instance) {
                is SplashChild -> {
                    SplashMainScreenMobile(navigator = navigator)
                }
                is OnboardingChild -> {
                    OnboardingMainScreenMobile(navigator = navigator)
                }
                is AuthChild -> {
                    AuthMainScreenMobile(navigator)
                }
                is VerificationChild -> VerificationMainScreenMobile(
                    phone = child.phone,
                    verificationToken = child.verificationToken,
                    navigator = navigator
                )
                is PinCodeChild -> PinCodeMainScreenMobile(
                    navigator = navigator,
                    mode = child.pinCodeMode
                )
                is SubscriptionChild -> {
                    SubscriptionMainScreen(navigator)
                }
                is HomeChild -> {
                    HomeMainScreen(navigator)
                }
            }
        }
    }
    subscribeOnSnackBar()
}