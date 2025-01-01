package splash

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.new_resources.language.Language
import splash.data.SplashAnimationState
import splash.data.SplashAnimationState.ICON

sealed interface Event: BaseEvent {
    data class RedirectAfterLanguageSet(val language: Language): Event
}

data class State(
    val animationState: SplashAnimationState = ICON
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateToHome: Effect
    data object NavigateToOnboarding: Effect
    data object ShowSelectLanguageDialog: Effect
}
