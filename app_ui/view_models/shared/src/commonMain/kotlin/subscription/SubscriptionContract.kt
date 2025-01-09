package subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import subscription.data.SubscriptionPeriod
import subscription.data.SubscriptionPeriod.Yearly

sealed interface Event: BaseEvent {
    data object SubscribeOrSkip: Event
    data class SelectSubscriptionPlan(val plan: SubscriptionPlan): Event
    data class ToggleSubscriptionPeriod(val period: SubscriptionPeriod): Event
}

sealed interface State: BaseState {
    data object Loading: State
    data object Error: State
    data class Success(
        val plans: List<SubscriptionPlan>,
        val selectedPlans: SubscriptionPlan,
        val period: SubscriptionPeriod = Yearly,
        val subscribeButtonState: ButtonState = ButtonState.DARK_GRAY_ACTIVE
    ): State
}

sealed interface Effect: BaseEffect {
    data object NavigateToHome: Effect
}

interface Listener {
    fun selectSubscriptionPlan(plan: SubscriptionPlan)
    fun toggleSubscriptionPeriod(period: SubscriptionPeriod)
    fun subscribeOrSkip()
}
