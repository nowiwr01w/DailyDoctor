package subscription

import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState
import components.button.ButtonState.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import subscription.SubscriptionContract.*
import subscription.data.SubscriptionType
import view_model.BaseViewModel

class SubscriptionViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.ChooseSubscriptionPlan -> chooseSubscriptionPlan(event.plan)
        }
    }

    private fun init() {
        loadSubscriptionPlans()
    }

    private fun loadSubscriptionPlans() = hide {
        runCatchingApp {
            delay(INITIAL_PROGRESS_DURATION) // TODO
        }.onSuccess {
            setState { copy(showInitProgress = false) }
            startTimer()
        }.onFailure {
            setEffect { Effect.NavigateToHome }
        }
    }

    private fun startTimer() = hide {
        (CONTINUE_BUTTON_SECONDS downTo 1).asSequence()
            .asFlow()
            .onEach {
                setState { copy(continueButtonSeconds = it) }
                delay(1000)
            }
            .onCompletion {
                setState { copy(continueButtonSeconds = 0) }
            }
            .collect()
    }

    private fun chooseSubscriptionPlan(plan: SubscriptionType) = hide {
        setState { copy(subscribeButtonState = SEND_REQUEST) }
        delay(3000)
        setState { copy(subscribeButtonState = SUCCESS) }
        delay(3000)
        setState { copy(subscribeButtonState = DEFAULT) }

    }

    companion object {
        const val CONTINUE_BUTTON_SECONDS = 5
        const val INITIAL_PROGRESS_DURATION = 3000L
    }
}