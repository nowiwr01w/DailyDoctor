package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigators.bottom_sheets

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Job
import navigation.navigators.BaseNavigator

interface MobileBottomSheetsNavigator: BaseNavigator {
    /** CONFIG **/
    val bottomSheetsChildSlot: Value<ChildSlot<navigation.config.config.BottomSheetNavigationConfig, navigation.config.child.BottomSheetChild>>

    /** BACK NAVIGATION **/
    var hideBottomSheetCallback: () -> Job
}
