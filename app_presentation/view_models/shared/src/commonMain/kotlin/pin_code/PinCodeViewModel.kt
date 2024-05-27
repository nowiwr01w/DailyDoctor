package pin_code

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.usecase.execute
import components.button.ButtonState.DEFAULT
import components.button.ButtonState.ERROR
import components.button.ButtonState.SUCCESS
import helpers.snack_bar.SnackBarHelper
import helpers.snack_bar.data.SnackBarParams
import helpers.snack_bar.data.SnackBarType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import model.message.AppMessage
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.Change
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.Check
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.Create
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.Delete
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.Repeat
import pin_code.PinCodeContract.Effect
import pin_code.PinCodeContract.Event
import pin_code.PinCodeContract.State
import pin_code.data.PinCodeOperation
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
            is PinCodeOperation.AddDigit -> "$pinCode${operation.digit}"
            is PinCodeOperation.RemoveDigit -> pinCode.dropLast(1)
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
            val request = CheckPinCodeRequest(code)
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
            runCatching {
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
            message = AppMessage.AppMessageText("Блят понедельник") // TODO
        )
        setState { copy(buttonState = SUCCESS) }
        snackBarHelper.showSnackBar(params)
        delay(3000)
        setEffect { navigationEffect }
    }

    private suspend fun onError(message: String) {
        setState { copy(buttonState = ERROR) }
        delay(3000)
        setState { copy(buttonState = DEFAULT) }
    }
}