package home

import home.HomeContract.*
import kotlinx.coroutines.CoroutineScope
import view_model.BaseViewModel

class HomeViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}