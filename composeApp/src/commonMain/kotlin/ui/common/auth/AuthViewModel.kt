package ui.common.auth

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.auth.AuthContract.Effect
import ui.common.auth.AuthContract.Event
import ui.common.auth.AuthContract.State

class AuthViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}