package navigation.config.child

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationChild
import view_model.BaseViewModelComponent

@Serializable
sealed class DialogsChild(
    open val isCancellable: Boolean = true
): BaseNavigationChild {
    /**
     * COMPONENT
     */
    override lateinit var baseComponent: BaseViewModelComponent
}
