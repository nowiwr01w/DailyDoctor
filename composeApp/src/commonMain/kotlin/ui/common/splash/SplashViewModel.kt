package ui.common.splash

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import logMessage
import ui.common.splash.SplashContract.*
import ui.common.splash.SplashContract.SplashAnimationState.*

class SplashViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

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
        (0..5000 step 100).asSequence()
            .asFlow()
            .onStart {
                // TODO: Send analytics with [splash_start] param
            }
            .onEach { millis ->
                logMessage("mi = $millis")
                setAnimationState(millis)
                delay(100)
            }
            .onCompletion {
                // TODO: Send analytics with [splash_end] param
            }
            .collect()
    }

    private fun setAnimationState(millis: Int) = setState {
        val animationState = when {
            millis >= 4000 -> SECOND_TEXT
            millis >= 3000 -> FIRST_TEXT
            millis >= 2000 -> ICON
            else -> PROGRESS
        }
        copy(animationState = animationState)
    }
}