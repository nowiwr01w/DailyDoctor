package splash

import com.nowiwr01p.model.resources.language.Language
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

sealed interface Event: BaseEvent {
    data class InitAppDataAfterLanguageSet(val language: Language): Event
}

data class State(
    val showProgress: Boolean = false
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateToHome: Effect
    data object NavigateToOnboarding: Effect
    data object ShowSelectLanguageDialog: Effect
}
