package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.bottom_sheets

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Job
import navigation.navigators.BaseNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileBottomSheetConfig

interface MobileBottomSheetsNavigator: BaseNavigator {
    /**
     * CONFIG
     */
    val bottomSheetsChildSlot: Value<ChildSlot<MobileBottomSheetConfig, MobileBottomSheetChild>>

    /**
     * BACK NAVIGATION
     */
    var hideBottomSheetCallback: () -> Unit

    /**
     * BOTTOM SHEETS
     */
    fun showTestBottomSheet()
}
