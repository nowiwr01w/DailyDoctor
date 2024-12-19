package navigation.navigators.bottom_sheets

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Job
import navigation.config.child.BottomSheetChild
import navigation.config.config.BottomSheetNavigationConfig
import navigation.navigators.BaseNavigator

interface BottomSheetsNavigator: BaseNavigator {
    /** CONFIG **/
    val bottomSheetsChildSlot: Value<ChildSlot<BottomSheetNavigationConfig, BottomSheetChild>>

    /** BACK NAVIGATION **/
    var hideBottomSheetCallback: () -> Job
}
