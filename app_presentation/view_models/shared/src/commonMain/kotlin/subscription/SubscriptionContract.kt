package subscription

import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import subscription.SubscriptionViewModel.Companion.CONTINUE_BUTTON_SECONDS
import subscription.data.SubscriptionPeriod
import subscription.data.SubscriptionPeriod.*
import subscription.data.SubscriptionType
import subscription.data.SubscriptionType.*

sealed interface Event: BaseEvent {
    data object SubscribeOrSkip: Event
    data class SelectSubscriptionPlan(val plan: SubscriptionType): Event
    data class ToggleSubscriptionPeriod(val period: SubscriptionPeriod): Event
}

data class State(
    val plan: SubscriptionType = Base,
    val period: SubscriptionPeriod = Yearly,
    val subscribeButtonState: ButtonState = ButtonState.DARK_GRAY_ACTIVE,
    val closeSecondsLeft: Int = CONTINUE_BUTTON_SECONDS
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateToHome: Effect
}

interface Listener {
    fun selectSubscriptionPlan(plan: SubscriptionType)
    fun toggleSubscriptionPeriod(period: SubscriptionPeriod)
    fun subscribeOrSkip()
}
