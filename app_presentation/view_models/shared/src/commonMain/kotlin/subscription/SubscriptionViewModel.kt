package subscription

import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.SUCCESS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import pro.respawn.flowmvi.api.PipelineContext
import subscription.Effect.NavigateToHome
import subscription.Event.SelectSubscriptionPlan
import subscription.Event.SubscribeOrSkip
import subscription.Event.ToggleSubscriptionPeriod
import subscription.data.SubscriptionPeriod
import subscription.data.SubscriptionType
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class SubscriptionViewModel: BaseViewModel<State, Event, Effect>(initialValue = State()) {
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
        runCatchingApp {
            delay(INITIAL_PROGRESS_DURATION) // TODO: Get subscription plans flow with [withRemote = false]
        }.onSuccess {
            startTimer()
        }.onFailure {
            setEffect(NavigateToHome)
        }
    }

    /**
     * TIMER
     */
    private fun Ctx.startTimer() = io {
        (CONTINUE_BUTTON_SECONDS downTo 1).asSequence() // TODO: Create TimerWork
            .asFlow()
            .onEach {
                setState { copy(closeSecondsLeft = it) }
                delay(1000)
            }
            .onCompletion {
                setState { copy(closeSecondsLeft = 0) }
            }
            .collect()
    }

    /**
     * SUBSCRIPTION SETTINGS
     */
    private suspend fun Ctx.chooseSubscriptionPlan(plan: SubscriptionType) {
        setState { copy(plan = plan) }
    }

    private suspend fun Ctx.toggleSubscriptionPeriod(period: SubscriptionPeriod) {
        setState { copy(period = period) }
    }

    /**
     * SUBSCRIBE
     */
    private fun Ctx.subscribeOrSkip() = io {
        withState {
            if (plan is SubscriptionType.Free) {
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

    companion object {
        const val CONTINUE_BUTTON_SECONDS = 5
        const val INITIAL_PROGRESS_DURATION = 3000L
    }
}
