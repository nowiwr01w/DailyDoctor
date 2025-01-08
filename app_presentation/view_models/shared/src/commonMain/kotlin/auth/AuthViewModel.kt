package auth

import auth.Event.HandleUserInput
import auth.Event.OnAuthClicked
import auth.Event.OnPrivacyPolicyClicked
import auth.Event.ToggleAuthMode
import auth.Event.ToggleUserInputVisibility
import auth.data.AuthType.SIGN_IN
import auth.data.AuthType.SIGN_UP
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.PinCodeTokenResponse
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.response.token.VerificationTokenResponse
import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.ERROR
import components.button.ButtonState.SUCCESS
import helpers.snack_bar.SnackBarHelper
import helpers.snack_bar.data.SnackBarParams.TopFloatingParams
import helpers.snack_bar.data.SnackBarType
import kotlinx.coroutines.delay
import model.errors.auth.AuthTextFieldType
import model.errors.auth.AuthTextFieldType.PASSWORD
import model.errors.auth.AuthTextFieldType.PASSWORD_CONFIRMATION
import model.errors.auth.AuthTextFieldType.PHONE
import model.message.AppMessage
import model.user.UserData
import model.user.UserDataSignIn
import model.user.UserDataSignUp
import pro.respawn.flowmvi.api.PipelineContext
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppValidateAuthDataUseCase
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class AuthViewModel(
    private val signInUseCase: AppSignInUseCase,
    private val signUpUseCase: AppSignUpUseCase,
    private val authDataValidator: AppValidateAuthDataUseCase,
    private val snackBarHelper: SnackBarHelper
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is HandleUserInput -> handleUserInput(event.type, event.value)
            is OnAuthClicked -> checkUserInput()
            is OnPrivacyPolicyClicked -> onPrivacyPolicyClicked()
            is ToggleAuthMode -> toggleAuthMode()
            is ToggleUserInputVisibility -> toggleUserInputVisibility()
        }
    }

    /**
     * USER INPUT
     */
    private suspend fun Ctx.handleUserInput(type: AuthTextFieldType, value: String) = setState {
        when (type) {
            PHONE -> copy(phone = value, authError = null)
            PASSWORD -> copy(password = value, authError = null)
            PASSWORD_CONFIRMATION -> copy(passwordConfirmation = value, authError = null)
        }
    }

    private fun Ctx.checkUserInput() = io {
        withState {
            val userData = when (authMode) {
                SIGN_IN -> UserDataSignIn(phone, password)
//                SIGN_UP -> UserDataSignUp(phone, password, passwordConfirmation)
                SIGN_UP -> UserDataSignUp("+995595016690", "Darina01!", "Darina01!") // TODO
            }
            authDataValidator.execute(userData).let { error ->
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
    }

    private suspend fun Ctx.toggleUserInputVisibility() = setState {
        copy(isUserInputHidden = !isUserInputHidden)
    }

    /**
     * AUTH
     */
    private fun Ctx.auth(userData: UserData) = io {
        setState { copy(buttonState = DARK_GRAY_PROGRESS) }
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

    private suspend fun Ctx.onAuthSucceed(phone: String, tokenResponse: TokenResponse) {
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
        setEffect(navigateToNextScreenEffect)
    }

    private suspend fun Ctx.onAuthFailed(errorMessage: String) {
        setState { copy(buttonState = ERROR) }
        showSnackBar(
            type = SnackBarType.ERROR,
            message = AppMessage.AppMessageText(errorMessage)
        )
        delay(3000)
        setState { copy(buttonState = DARK_GRAY_ACTIVE) }
    }

    /**
     * PRIVACY POLICY
     */
    private fun onPrivacyPolicyClicked() {

    }

    /**
     * AUTH MODE
     */
    private suspend fun Ctx.toggleAuthMode() = withState {
        val authMode = if (authMode == SIGN_IN) SIGN_UP else SIGN_IN
        setState { copy(authMode = authMode) }
    }

    /**
     * SNACK BAR
     */
    private fun showSnackBar(type: SnackBarType, message: AppMessage) {
        val params = TopFloatingParams(type = type, message = message)
        snackBarHelper.showSnackBar(params)
    }
}
