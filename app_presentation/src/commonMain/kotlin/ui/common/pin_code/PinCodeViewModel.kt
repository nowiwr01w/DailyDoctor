package ui.common.pin_code

import view_model.BaseViewModel
import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import ui.common.pin_code.PinCodeContract.Effect
import ui.common.pin_code.PinCodeContract.Event
import ui.common.pin_code.PinCodeContract.State
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.*
import ui.common.pin_code.data.PinCodeOperation
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase

class PinCodeViewModel(
    scope: CoroutineScope,
    private val checkPinCodeUseCase: AppCheckPinCodeUseCase,
    private val createPinCodeUseCase: AppCreatePinCodeUseCase,
    private val changePinCodeUseCase: AppChangePinCodeUseCase,
    private val deletePinCodeUseCase: AppDeletePinCodeUseCase
): BaseViewModel<Event, State, Effect>(scope)  {

    private lateinit var previousPinCodeMode: PinCodeMode

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init(event.mode)
            is Event.HandleUserInput -> handleUserInput(event.operation)
        }
    }

    private fun init(mode: PinCodeMode) {
        previousPinCodeMode = mode
        setState { copy(pinCodeMode = mode) }
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
                CREATE, CHANGE -> setState { copy(pinCodeMode = REPEAT) }
                CHECK -> checkPinCode(pinCode)
                DELETE -> deletePinCode()
                REPEAT -> when (previousPinCodeMode) {
                    CREATE -> createPinCode(pinCode)
                    else -> changePinCode(pinCode)
                }
            }
        }
    }

    private fun createPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = CreatePinCodeRequest(code)
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
        pinCodeCallback = { deletePinCodeUseCase.execute() },
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
                setEffect { navigationEffect }
            }.onFailure { error ->
                if (!error.message.isNullOrEmpty()) {
                    onError(error.message!!)
                }
            }
        }
    }

    private fun onError(message: String) {

    }
}