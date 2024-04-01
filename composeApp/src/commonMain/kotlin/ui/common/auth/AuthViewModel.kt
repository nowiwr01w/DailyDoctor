package ui.common.auth

import base.view_model.BaseViewModel
import domain.model.user.UserData
import domain.model.user.UserDataSignIn
import domain.model.user.UserDataSignUp
import domain.repository.auth.data.errors.AuthTextFieldType
import domain.repository.auth.data.errors.AuthTextFieldType.EMAIL
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD_CONFIRMATION
import domain.usecase.auth.SignInUseCase
import domain.usecase.auth.SignUpUseCase
import domain.usecase.auth.ValidateAuthDataUseCase
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
import ui.common.auth.data.AuthType.SIGN_IN
import ui.common.auth.data.AuthType.SIGN_UP
import ui.core_ui.components.ButtonState.SEND_REQUEST

class AuthViewModel(
    scope: CoroutineScope,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val authDataValidator: ValidateAuthDataUseCase,
): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is HandleUserInput -> handleUserInput(event.type, event.value)
            is OnAuthClicked -> checkUserInput()
            is OnPrivacyPolicyClicked -> onPrivacyPolicyClicked()
            is ToggleAuthMode -> toggleAuthMode()
            is ToggleUserInputVisibility -> toggleUserInputVisibility()
        }
    }

    private fun init() {

    }

    private fun handleUserInput(type: AuthTextFieldType, value: String) = setState {
        when (type) {
            EMAIL -> copy(email = value, authError = null)
            PASSWORD -> copy(password = value, authError = null)
            PASSWORD_CONFIRMATION -> copy(passwordConfirmation = value, authError = null)
        }
    }

    private fun checkUserInput() = hide {
        getUserData().let { userData ->
            val error = authDataValidator.execute(userData)
            if (error == null) {
                auth(userData)
            } else {
                // TODO: Show error snack bars
            }
            setState { copy(authError = error) }
        }
    }

    private fun getUserData() = with(viewState.value) {
        when (viewState.value.authMode) {
            SIGN_IN -> UserDataSignIn(email.trim(), password)
            SIGN_UP -> UserDataSignUp(email.trim(), password, passwordConfirmation)
        }
    }

    private fun auth(userData: UserData) = hide {
        setState { copy(buttonState = SEND_REQUEST) }
        runCatching {
            when (userData) {
                is UserDataSignIn -> signInUseCase.execute(userData)
                is UserDataSignUp -> signUpUseCase.execute(userData)
            }
        }.onSuccess {
            // TODO: Init app data + check verification
        }.onFailure {
            // TODO: Show error via AuthButton
        }
    }

    private fun onPrivacyPolicyClicked() {

    }

    private fun toggleAuthMode() {
        val authMode = if (viewState.value.authMode == SIGN_IN) SIGN_UP else SIGN_IN
        setState { copy(authMode = authMode) }
    }

    private fun toggleUserInputVisibility() = setState {
        copy(isUserInputHidden = !isUserInputHidden)
    }
}