package navigation.navigators.dialogs

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import navigation.config.child.DialogChild
import navigation.config.config.DialogNavigationConfig
import navigation.navigators.BaseNavigator

interface DialogsNavigator: BaseNavigator {
    /** CONFIG **/
    val dialogsChildSlot: Value<ChildSlot<DialogNavigationConfig, DialogChild>>

    /** BACK NAVIGATION **/
    var hideDialogCallback: () -> Unit
}
