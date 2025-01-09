package navigation.navigators.dialogs

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import navigation.config.child.DialogsChild
import navigation.config.config.DialogsNavigationConfig
import navigation.navigators.BaseNavigator

interface DialogsNavigator: BaseNavigator {
    /**
     * CONFIG
     */
    val dialogsChildSlot: Value<ChildSlot<DialogsNavigationConfig, DialogsChild>>

    /**
     * BACK NAVIGATION
     */
    var hideDialogCallback: () -> Unit

    /**
     * DIALOGS
     */
    fun showExitAppDialog()
    fun showSelectLanguageDialog(isFirstSelection: Boolean)
}
