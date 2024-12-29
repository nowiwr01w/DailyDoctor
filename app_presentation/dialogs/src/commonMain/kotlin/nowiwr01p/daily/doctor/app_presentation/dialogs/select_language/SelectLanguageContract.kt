package nowiwr01p.daily.doctor.app_presentation.dialogs.select_language

import com.nowiwr01p.model.model.language.Language
import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import model.language.AppLanguageData

sealed interface Event: BaseEvent {
    data object OnCloseClicked: Event
    data class OnSelectLanguageClicked(val language: Language): Event
    data object OnConfirmSelectedLanguage: Event
}

data class State(
    val allLanguages: List<Language> = listOf(),
    val selectedLanguage: Language = Language.Georgian,
    val selectButtonState: ButtonState = ButtonState.ORANGE_ACTIVE
): BaseState

sealed interface Effect: BaseEffect {
    data object CloseDialog: Effect
}

interface Listener {
    fun onCloseClick()
    fun onSelectLanguageClick(language: Language)
    fun onConfirmSelectedLanguage()
}
