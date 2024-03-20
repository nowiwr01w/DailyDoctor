package ui.common.splash

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.splash.SplashContract.*

class SplashViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}