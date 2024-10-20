package subscription

import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import subscription.SubscriptionViewModel.Companion.CONTINUE_BUTTON_SECONDS
import subscription.data.SubscriptionType

interface SubscriptionContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
        data class ChooseSubscriptionPlan(val plan: SubscriptionType): Event
    }

    data class State(
        val subscribeButtonState: ButtonState = ButtonState.DEFAULT,
        val closeSecondsLeft: Int = CONTINUE_BUTTON_SECONDS
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
    }

    interface Listener {
        fun chooseSubscriptionPlan(plan: SubscriptionType)
    }
}