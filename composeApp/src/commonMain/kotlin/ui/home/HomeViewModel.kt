package ui.home

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.home.HomeContract.*

class HomeViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {
    
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        
    }
}