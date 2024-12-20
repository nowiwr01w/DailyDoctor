package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import kotlinx.coroutines.Job
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileBottomSheetConfig
import navigation.navigators.getDefaultBackCallback
import view_model.BaseViewModelComponent

class MobileBottomSheetsNavigatorImpl(
    appNavigationContext: ComponentContext,
    private val navigation: SlotNavigation<MobileBottomSheetConfig>
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
                val backCallback = getDefaultBackCallback(isCancellable)
                baseComponent = BaseViewModelComponent(childContext).apply {
                    backHandler.register(backCallback)
                }
            }
        }
    )

    /**
     * BACK NAVIGATION
     */
    override lateinit var hideBottomSheetCallback: () -> Job

    override fun navigateBack(onComplete: (isSuccess: Boolean) -> Unit) {
        hideBottomSheetCallback().invokeOnCompletion {
            navigation.dismiss(onComplete = onComplete)
        }
    }
}
