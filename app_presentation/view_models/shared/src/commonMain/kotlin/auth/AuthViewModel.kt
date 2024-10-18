package auth

import view_model.BaseViewModel
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.token.VerificationTokenResponse
import model.message.AppMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import model.errors.auth.AuthTextFieldType
import model.errors.auth.AuthTextFieldType.PHONE
import model.errors.auth.AuthTextFieldType.PASSWORD
import model.errors.auth.AuthTextFieldType.PASSWORD_CONFIRMATION
import model.user.UserData
import model.user.UserDataSignIn
import model.user.UserDataSignUp
import auth.AuthContract.Effect
import auth.AuthContract.Event
import auth.AuthContract.Event.HandleUserInput
import auth.AuthContract.Event.Init
import auth.AuthContract.Event.OnAuthClicked
import auth.AuthContract.Event.OnPrivacyPolicyClicked
import auth.AuthContract.Event.ToggleAuthMode
import auth.AuthContract.Event.ToggleUserInputVisibility
import auth.AuthContract.State
import auth.data.AuthType.SIGN_IN
import auth.data.AuthType.SIGN_UP
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState.DEFAULT
import components.button.ButtonState.ERROR
import components.button.ButtonState.SEND_REQUEST
import components.button.ButtonState.SUCCESS
import helpers.snack_bar.SnackBarHelper
import helpers.snack_bar.data.SnackBarParams.TopFloatingParams
import helpers.snack_bar.data.SnackBarType
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppValidateAuthDataUseCase

class AuthViewModel(
    scope: CoroutineScope,
    private val signInUseCase: AppSignInUseCase,
    private val signUpUseCase: AppSignUpUseCase,
    private val authDataValidator: AppValidateAuthDataUseCase,
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
        // TODO
    }

    private fun handleUserInput(type: AuthTextFieldType, value: String) = setState {
        when (type) {
            PHONE -> copy(phone = value, authError = null)
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
            SIGN_IN -> UserDataSignIn(phone, password)
            SIGN_UP -> UserDataSignUp(phone, password, passwordConfirmation)
        }
    }

    private fun auth(userData: UserData) = hide {
        setState { copy(buttonState = SEND_REQUEST) }
        runCatchingApp {
            when (userData) {
                is UserDataSignIn -> {
                    val request = SignInRequest(phone = userData.phone, password = userData.password)
                    signInUseCase.execute(request)
                }
                is UserDataSignUp -> {
                    val request = SignUpRequest(phone = userData.phone, password = userData.password)
                    signUpUseCase.execute(request)
                }
            }
        }.onSuccess { tokenResponse ->
            onAuthSucceed(userData.phone, tokenResponse)
            // TODO: Init app data + check verification
        }.onFailure { error ->
            onAuthFailed(error.message.orEmpty())
        }
    }

    private suspend fun onAuthSucceed(phone: String, tokenResponse: TokenResponse) {
        setState { copy(buttonState = SUCCESS) }
        delay(3000)
        val navigateToNextScreenEffect = when (tokenResponse) {
            is VerificationTokenResponse -> Effect.NavigateToVerification(
                phone = phone,
                token = tokenResponse.token
            )
            is PinCodeTokenResponse -> Effect.NavigateToPin(
                token = tokenResponse.token,
                isPinCodeSet = tokenResponse.isPinCodeSet
            )
            else -> throw IllegalStateException("Unexpected token instance.")
        }
        setEffect { navigateToNextScreenEffect }
    }

    private suspend fun onAuthFailed(errorMessage: String) {
        setState { copy(buttonState = ERROR) }
        showSnackBar(
            type = SnackBarType.ERROR,
            message = AppMessage.AppMessageText(errorMessage)
        )
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