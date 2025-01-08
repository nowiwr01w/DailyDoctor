package nowiwr01p.daily.doctor.app_ui.dialogs.select_language

import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.defaultSelectedLanguage
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

sealed interface Event: BaseEvent {
    data object OnCloseClicked: Event
    data class OnSelectLanguageClicked(val language: Language): Event
    data object OnConfirmSelectedLanguage: Event
}

data class State(
    val allLanguages: List<Language> = listOf(),
    val selectedLanguage: Language = defaultSelectedLanguage
): BaseState

sealed interface Effect: BaseEffect {
    data class CloseDialog(val selectedLanguage: Language): Effect
}

interface Listener {
    fun onCloseClick()
    fun onSelectLanguageClick(language: Language)
    fun onConfirmSelectedLanguage()
}
