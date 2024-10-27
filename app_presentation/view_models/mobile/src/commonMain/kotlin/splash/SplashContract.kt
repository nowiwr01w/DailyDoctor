package splash

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import splash.data.SplashAnimationState
import splash.data.SplashAnimationState.ICON

interface SplashContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val animationState: SplashAnimationState = ICON
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
        data object NavigateToOnboarding: Effect
    }
}