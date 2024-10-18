package subscription

import com.nowiwr01p.model.extensions.runCatchingApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import subscription.SubscriptionContract.*
import subscription.SubscriptionContract.Companion.CONTINUE_BUTTON_SECONDS
import view_model.BaseViewModel

class SubscriptionViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private fun init() {
        loadSubscriptionPlans()
    }

    private fun loadSubscriptionPlans() = hide {
        runCatchingApp {
            delay(3000) // TODO
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
}