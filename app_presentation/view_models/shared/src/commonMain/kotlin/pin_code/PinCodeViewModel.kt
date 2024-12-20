package pin_code

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.usecase.execute
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.ERROR
import components.button.ButtonState.SUCCESS
import helpers.snack_bar.SnackBarHelper
import helpers.snack_bar.data.SnackBarParams
import helpers.snack_bar.data.SnackBarType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import model.message.AppMessage
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode.*
import pin_code.PinCodeContract.Effect
import pin_code.PinCodeContract.Event
import pin_code.PinCodeContract.State
import pin_code.data.PinCodeOperation
import pin_code.data.PinCodeOperation.*
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase
import view_model.BaseViewModel

class PinCodeViewModel(
    scope: CoroutineScope,
    private val pinCodeMode: PinCodeMode,
    private val snackBarHelper: SnackBarHelper,
    private val checkPinCodeUseCase: AppCheckPinCodeUseCase,
    private val createPinCodeUseCase: AppCreatePinCodeUseCase,
    private val changePinCodeUseCase: AppChangePinCodeUseCase,
    private val deletePinCodeUseCase: AppDeletePinCodeUseCase
): BaseViewModel<Event, State, Effect>(scope)  {

    private var previousPinCodeMode = pinCodeMode

    override fun setInitialState() = State(pinCodeMode = pinCodeMode)

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.HandleUserInput -> handleUserInput(event.operation)
        }
    }

    private fun handleUserInput(operation: PinCodeOperation) {
        updateUserInput(operation)
        handleModeChanging()
    }

    private fun updateUserInput(operation: PinCodeOperation) = with(viewState.value) {
        val updatedPinCode = when (operation) {
            is AddDigit -> "$pinCode${operation.digit}"
            is RemoveDigit -> pinCode.dropLast(1)
        }
        setState { copy(pinCode = updatedPinCode) }
    }

    private fun handleModeChanging() = with(viewState.value) {
        if (pinCode.length == 4) {
            when (pinCodeMode) {
                is Create, is Change -> createPinCode(pinCode) // TODO: setState { copy(pinCodeMode = Repeat) }
                is Check -> checkPinCode(pinCode)
                is Delete -> deletePinCode()
                is Repeat -> when (previousPinCodeMode) {
                    is Create -> createPinCode(pinCode)
                    else -> changePinCode(pinCode)
                }
            }
        }
    }

    private fun createPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = CreatePinCodeRequest(
                code = code,
                pinCodeToken = previousPinCodeMode.pinCodeToken
            )
            createPinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateToHome
    )

    private fun checkPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = CheckPinCodeRequest(
                code = code,
                checkPinCodeToken = pinCodeMode.pinCodeToken
            )
            checkPinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateToHome
    )

    private fun changePinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = ChangePinCodeRequest()
            changePinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateBack
    )

    private fun deletePinCode() = pinCodeOperation(
        pinCodeCallback = {
            deletePinCodeUseCase.execute()
        },
        navigationEffect = Effect.NavigateBack
    )

    private fun pinCodeOperation(
        pinCodeCallback: suspend () -> Unit,
        navigationEffect: Effect
    ): Job {
        return hide {
            runCatchingApp {
                pinCodeCallback()
            }.onSuccess {
                onSuccess(navigationEffect)
            }.onFailure { error ->
                if (!error.message.isNullOrEmpty()) {
                    onError(error.message!!)
                }
            }
        }
    }

    private suspend fun onSuccess(navigationEffect: Effect) {
        val params = SnackBarParams.TopFloatingParams(
            type = SnackBarType.SUCCESS,
            message = AppMessage.AppMessageText("Добро пожаловать!") // TODO
        )
        setState { copy(buttonState = SUCCESS) }
        snackBarHelper.showSnackBar(params)
        delay(3000)
        setEffect { navigationEffect }
    }

    private suspend fun onError(message: String) {
        val params = SnackBarParams.TopFloatingParams(
            type = SnackBarType.ERROR,
            message = AppMessage.AppMessageText(message) // TODO
        )
        setState { copy(buttonState = ERROR) }
        snackBarHelper.showSnackBar(params)
        delay(3000)
        setState { copy(buttonState = DARK_GRAY_ACTIVE, pinCode = "") }
    }
}
