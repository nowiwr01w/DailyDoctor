package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import constants.COMPONENT_TRANSITION_ANIMATION_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import navigation.navigators.getDefaultBackCallback
import navigation.navigators.screen_results.ScreenResultHandler
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileBottomSheetConfig
import view_model.BaseViewModelComponent

class MobileBottomSheetsNavigatorImpl(
    appNavigationContext: ComponentContext,
    private val appScope: AppScope,
    private val dispatchers: AppDispatchers,
    private val navigation: SlotNavigation<MobileBottomSheetConfig>,
    private val screenResultHandler: ScreenResultHandler
): MobileBottomSheetsNavigator, ComponentContext by appNavigationContext {
    /**
     * CONFIG
     */
    override val bottomSheetsChildSlot = childSlot(
        key = "BottomSheetsChildSlot",
        source = navigation,
        serializer = MobileBottomSheetConfig.serializer(),
        childFactory = { config, childContext ->
            config.child.apply {
                resultHandler = screenResultHandler
                baseComponent = BaseViewModelComponent(childContext).apply {
                    backHandler.register(getDefaultBackCallback(isCancellable))
                }
            }
        }
    )

    /**
     * BACK NAVIGATION
     */
    override lateinit var hideBottomSheetCallback: () -> Unit

    override fun navigateBack(onComplete: (isSuccess: Boolean) -> Unit) {
        appScope.scope.launch(dispatchers.main) {
            hideBottomSheetCallback()
            delay(COMPONENT_TRANSITION_ANIMATION_DURATION.toLong())
            navigation.dismiss(onComplete = onComplete)
        }
    }

    /**
     * BOTTOM SHEETS
     */
    override fun showTestBottomSheet() {
        navigation.activate(MobileBottomSheetConfig.Test)
    }
}
