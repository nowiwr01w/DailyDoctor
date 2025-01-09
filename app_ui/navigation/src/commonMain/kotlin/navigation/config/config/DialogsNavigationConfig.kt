package navigation.config.config

import kotlinx.serialization.Serializable
import navigation.config.BaseNavigationConfig
import navigation.config.child.DialogsChild
import navigation.config.child.DialogsChild.ExitAppChild
import navigation.config.child.DialogsChild.SelectLanguageChild

@Serializable
sealed class DialogsNavigationConfig(val child: DialogsChild): BaseNavigationConfig {
    @Serializable
    data object ExitApp: DialogsNavigationConfig(child = ExitAppChild)

    @Serializable
    data class SelectLanguage(val isFirstSelection: Boolean): DialogsNavigationConfig(
        child = SelectLanguageChild(isFirstSelection = isFirstSelection)
    )
}
