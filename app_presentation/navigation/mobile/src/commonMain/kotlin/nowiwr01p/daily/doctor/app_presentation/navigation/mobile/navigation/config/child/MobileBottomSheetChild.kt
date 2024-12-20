package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationChild
import view_model.BaseViewModelComponent

@Serializable
sealed class MobileBottomSheetChild(
    open val isCancellable: Boolean = true
): BaseNavigationChild {
    /**
     * COMPONENT
     */
    override lateinit var baseComponent: BaseViewModelComponent
}
