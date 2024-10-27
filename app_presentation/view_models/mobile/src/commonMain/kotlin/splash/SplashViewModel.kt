package splash

import com.nowiwr01p.model.usecase.execute
import view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import splash.SplashContract.Effect
import splash.SplashContract.Effect.*
import splash.SplashContract.Event
import splash.SplashContract.State
import splash.data.SplashAnimationState
import user.usecase.GetLocalUserUseCase

class SplashViewModel(
    scope: CoroutineScope,
    private val getLocalUserUseCase: GetLocalUserUseCase
): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private fun init() {
        startTimer()
    }

    private fun startTimer() = hide {
        val animationDuration = SplashAnimationState.entries
            .last()
            .showUntilAtMillis
        (0..animationDuration step 100).asSequence()
            .asFlow()
            .onStart {
                // TODO: Send analytics with [splash_start] param
            }
            .onEach { millis ->
                setAnimationState(millis)
                delay(100)
            }
            .onCompletion {
                // TODO: Send analytics with [splash_end] param
                chooseNavigationDestination()
            }
            .collect()
    }

    private fun setAnimationState(millis: Int) = setState {
        val animationState = SplashAnimationState.entries.first { item ->
            millis <= item.showUntilAtMillis
        }
        copy(animationState = animationState)
    }

    private suspend fun chooseNavigationDestination() {
        val effect = when {
            getLocalUserUseCase.execute() != null -> NavigateToHome
            else -> NavigateToOnboarding
        }
        setEffect { effect }
    }
}