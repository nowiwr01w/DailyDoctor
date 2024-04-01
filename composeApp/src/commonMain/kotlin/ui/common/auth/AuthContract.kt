package ui.common.auth

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.core.ButtonState
import ui.core.ButtonState.DEFAULT

interface AuthContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
        data object ToggleAuthMode: Event
        data object ToggleUserInputVisibility: Event
        data object OnPrivacyPolicyClicked: Event
        data object HandleUserInput: Event
        data object OnAuthClicked: Event
    }

    data class State(
        val authMode: String = "",
        val email: String = "",
        val passwordConfirmation: String = "",
        val password: String = "",
        val buttonState: ButtonState = DEFAULT,
        val privacyPolicyUrl: String = ""
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToVerification: Effect
        data object NavigateToPrivacyPolicyInfo: Effect
    }

    interface Listener {
        fun onUserInputChanged(input: String)
        fun onToggleUserInputVisibilityClicked()
        fun onToggleAuthModeClicked()
        fun onAuthClicked()
        fun onPrivacyPolicyClicked()
    }
}