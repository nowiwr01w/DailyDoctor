package splash

import com.nowiwr01p.model.resources.language.Language
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import splash.data.SplashAnimationState
import splash.data.SplashAnimationState.ICON

sealed interface Event: BaseEvent {
    data class InitAppDataAfterLanguageSet(val language: Language): Event
}

data class State(
    val animationState: SplashAnimationState = ICON
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateToHome: Effect
    data object NavigateToOnboarding: Effect
    data object ShowSelectLanguageDialog: Effect
}
