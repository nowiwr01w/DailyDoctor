package navigation.config.child

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationChild
import navigation.navigators.screen_results.ScreenResultHandler
import view_model.BaseViewModelComponent

@Serializable
sealed class DialogsChild(
    open val isCancellable: Boolean = true
): BaseNavigationChild {
    /**
     * COMPONENT
     */
    override lateinit var baseComponent: BaseViewModelComponent

    /**
     * SCREEN RESULT HANDLER
     */
    override lateinit var resultHandler: ScreenResultHandler

    /**
     * DIALOGS
     */
    @Serializable
    data object ExitAppChild: DialogsChild()

    @Serializable
    data class SelectLanguageChild(val isFirstSelection: Boolean): DialogsChild(
        isCancellable = !isFirstSelection
    )
}
