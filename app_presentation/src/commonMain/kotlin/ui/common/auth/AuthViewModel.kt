package ui.common.auth

import base.view_model.BaseViewModel
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import core.AppMessage
import domain.model.user.UserData
import domain.model.user.UserDataSignIn
import domain.model.user.UserDataSignUp
import domain.repository.auth.data.errors.AuthTextFieldType
import domain.repository.auth.data.errors.AuthTextFieldType.EMAIL
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD_CONFIRMATION
import domain.usecase.auth.ValidateAuthDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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
import ui.core_ui.components.button.ButtonState.DEFAULT
import ui.core_ui.components.button.ButtonState.ERROR
import ui.core_ui.components.button.ButtonState.SEND_REQUEST
import ui.core_ui.components.button.ButtonState.SUCCESS
import ui.core_ui.helpers.snack_bar.SnackBarHelper
import ui.core_ui.helpers.snack_bar.data.SnackBarParams.TopFloatingParams
import ui.core_ui.helpers.snack_bar.data.SnackBarType
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase

class AuthViewModel(
    scope: CoroutineScope,
    private val signInUseCase: AppSignInUseCase,
    private val signUpUseCase: AppSignUpUseCase,
    private val authDataValidator: ValidateAuthDataUseCase,
    private val snackBarHelper: SnackBarHelper
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
                showSnackBar(
                    type = SnackBarType.ERROR,
                    message = AppMessage.AppMessageText(error.message)
                )
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
                is UserDataSignIn -> {
                    val request = SignInRequest(email = userData.email, password = userData.password)
                    signInUseCase.execute(request)
                }
                is UserDataSignUp -> {
                    val request = SignUpRequest(email = userData.email, password = userData.password)
                    signUpUseCase.execute(request)
                }
            }
        }.onSuccess {
            onAuthSucceed()
            // TODO: Init app data + check verification
        }.onFailure {
            onAuthFailed()
        }
    }

    private suspend fun onAuthSucceed() {
        setState { copy(buttonState = SUCCESS) }
        // TODO: Check if user is verified = show SnackBar and navigate to home
        showSnackBar(
            type = SnackBarType.SUCCESS,
            message = AppMessage.AppMessageText("Добро пожаловать!")
        )
        delay(3000)
        setEffect { Effect.NavigateToVerification }
    }

    private suspend fun onAuthFailed() {
        setState { copy(buttonState = ERROR) }
        delay(3000)
        setState { copy(buttonState = DEFAULT) }
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

    private fun showSnackBar(type: SnackBarType, message: AppMessage) {
        val params = TopFloatingParams(type = type, message = message)
        snackBarHelper.showSnackBar(params)
    }
}