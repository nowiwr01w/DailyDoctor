package auth

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import model.errors.auth.AuthError
import model.errors.auth.AuthTextFieldType
import auth.data.AuthType
import auth.data.AuthType.SIGN_UP
import components.button.ButtonState
import components.button.ButtonState.DEFAULT

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
        val phone: String = "",
        val password: String = "",
        val passwordConfirmation: String = "",
        val authError: AuthError? = null,
        val isUserInputHidden: Boolean = true,
        val buttonState: ButtonState = DEFAULT,
        val privacyPolicyUrl: String = ""
    ): BaseState

    sealed interface Effect: BaseEffect {
        data class NavigateToPin(val isPinCodeSet: Boolean, val token: String): Effect
        data class NavigateToVerification(val phone: String, val token: String): Effect
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