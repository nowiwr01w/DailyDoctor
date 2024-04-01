package ui.common.auth

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.auth.AuthContract.Effect
import ui.common.auth.AuthContract.Event
import ui.common.auth.AuthContract.Event.HandleUserInput
import ui.common.auth.AuthContract.Event.Init
import ui.common.auth.AuthContract.Event.OnAuthClicked
import ui.common.auth.AuthContract.Event.OnPrivacyPolicyClicked
import ui.common.auth.AuthContract.Event.ToggleAuthMode
import ui.common.auth.AuthContract.Event.ToggleUserInputVisibility
import ui.common.auth.AuthContract.State

class AuthViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is HandleUserInput -> handleUserInput()
            is OnAuthClicked -> onAuthClicked()
            is OnPrivacyPolicyClicked -> onPrivacyPolicyClicked()
            is ToggleAuthMode -> toggleAuthMode()
            is ToggleUserInputVisibility -> toggleUserInputVisibility()
        }
    }

    private fun init() {

    }

    private fun handleUserInput() {

    }

    private fun onAuthClicked() {

    }

    private fun onPrivacyPolicyClicked() {

    }

    private fun toggleAuthMode() {

    }

    private fun toggleUserInputVisibility() {

    }
}