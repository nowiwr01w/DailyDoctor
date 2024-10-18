package subscription

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

interface SubscriptionContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val showInitProgress: Boolean = true,
        val continueButtonSeconds: Int = CONTINUE_BUTTON_SECONDS
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
    }

    interface Listener {

    }

    companion object {
        const val CONTINUE_BUTTON_SECONDS = 3
    }
}