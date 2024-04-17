package ui.common.auth

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import domain.repository.auth.data.errors.AuthError
import domain.repository.auth.data.errors.AuthTextFieldType
import ui.common.auth.data.AuthType
import ui.common.auth.data.AuthType.SIGN_UP
import ui.core_ui.components.button.ButtonState
import ui.core_ui.components.button.ButtonState.DEFAULT

interface AuthContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
        data object ToggleAuthMode: Event
        data object ToggleUserInputVisibility: Event
        data object OnPrivacyPolicyClicked: Event
        data class HandleUserInput(val type: AuthTextFieldType, val value: String): Event
        data object OnAuthClicked: Event
    }

    data class State(
        val authMode: AuthType = SIGN_UP,
        val email: String = "",
        val password: String = "",
        val passwordConfirmation: String = "",
        val authError: AuthError? = null,
        val isUserInputHidden: Boolean = true,
        val buttonState: ButtonState = DEFAULT,
        val privacyPolicyUrl: String = ""
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToVerification: Effect
        data object NavigateToPrivacyPolicyInfo: Effect
    }

    interface Listener {
        fun onUserInputChanged(type: AuthTextFieldType, value: String)
        fun onToggleUserInputVisibilityClicked()
        fun onToggleAuthModeClicked()
        fun onAuthClicked()
        fun onPrivacyPolicyClicked()
    }
}