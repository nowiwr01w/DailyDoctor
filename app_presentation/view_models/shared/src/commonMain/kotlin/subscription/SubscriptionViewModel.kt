package subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.SUCCESS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNot
import manager.subscription.AppSubscriptionManager
import pro.respawn.flowmvi.api.PipelineContext
import subscription.Effect.NavigateToHome
import subscription.Event.SelectSubscriptionPlan
import subscription.Event.SubscribeOrSkip
import subscription.Event.ToggleSubscriptionPeriod
import subscription.data.SubscriptionPeriod
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class SubscriptionViewModel(
    private val appSubscriptionManager: AppSubscriptionManager
): BaseViewModel<State, Event, Effect>(initialValue = State.Loading) {
    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is SubscribeOrSkip -> subscribeOrSkip()
            is SelectSubscriptionPlan -> chooseSubscriptionPlan(event.plan)
            is ToggleSubscriptionPeriod -> toggleSubscriptionPeriod(event.period)
        }
    }

    override suspend fun Ctx.init() {
        loadSubscriptionPlans()
    }

    /**
     * SUBSCRIPTION PLANS
     */
    private fun Ctx.loadSubscriptionPlans() = io {
        appSubscriptionManager.getSubscriptionPlans(withRemote = false)
            .filterNot { plans ->
                plans.isEmpty()
            }
            .catch {
                setState { State.Error }
            }
            .collectLatest { plans ->
                setState { State.Success(plans = plans, selectedPlans = plans[1]) }
            }
    }

    /**
     * SUBSCRIPTION SETTINGS
     */
    private suspend fun Ctx.chooseSubscriptionPlan(plan: SubscriptionPlan) = withState {
        if (this is State.Success) {
            setState { copy(selectedPlans = plan) }
        }
    }

    private suspend fun Ctx.toggleSubscriptionPeriod(period: SubscriptionPeriod) = withState {
        if (this is State.Success) {
            setState { copy(period = period) }
        }
    }

    /**
     * SUBSCRIBE
     */
    private fun Ctx.subscribeOrSkip() = io {
        withState {
            if (this is State.Success) {
                if (selectedPlans == plans.firstOrNull()) {
                    // TODO: Send analytics
                    setEffect(NavigateToHome)
                }
                setState { copy(subscribeButtonState = DARK_GRAY_PROGRESS) }
                delay(3000)
                setState { copy(subscribeButtonState = SUCCESS) }
                delay(3000)
                setState { copy(subscribeButtonState = DARK_GRAY_ACTIVE) }
            }
        }
    }
}
