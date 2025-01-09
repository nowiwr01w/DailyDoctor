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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import model.message.AppMessage
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode.Change
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode.Check
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode.Create
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode.Delete
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode.Repeat
import pin_code.Event.HandleUserInput
import pin_code.data.PinCodeOperation
import pin_code.data.PinCodeOperation.AddDigit
import pin_code.data.PinCodeOperation.RemoveDigit
import pro.respawn.flowmvi.api.PipelineContext
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCase
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class PinCodeViewModel(
    private val pinCodeMode: PinCodeMode,
    private val snackBarHelper: SnackBarHelper,
    private val checkPinCodeUseCase: AppCheckPinCodeUseCase,
    private val createPinCodeUseCase: AppCreatePinCodeUseCase,
    private val changePinCodeUseCase: AppChangePinCodeUseCase,
    private val deletePinCodeUseCase: AppDeletePinCodeUseCase
): BaseViewModel<State, Event, Effect>(
    initialValue = State(pinCodeMode = pinCodeMode)
) {
    private var previousPinCodeMode = pinCodeMode

    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is HandleUserInput -> handleUserInput(event.operation)
        }
    }

    private suspend fun Ctx.handleUserInput(operation: PinCodeOperation) {
        updateUserInput(operation)
        handleModeChanging()
    }

    private suspend fun Ctx.updateUserInput(operation: PinCodeOperation) = withState {
        val updatedPinCode = when (operation) {
            is AddDigit -> "$pinCode${operation.digit}"
            is RemoveDigit -> pinCode.dropLast(1)
        }
        setState { copy(pinCode = updatedPinCode) }
    }

    private suspend fun Ctx.handleModeChanging() = withState {
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

    private fun Ctx.createPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = CreatePinCodeRequest(
                code = code,
                pinCodeToken = previousPinCodeMode.pinCodeToken
            )
            createPinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateToHome
    )

    private fun Ctx.checkPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = CheckPinCodeRequest(
                code = code,
                checkPinCodeToken = pinCodeMode.pinCodeToken
            )
            checkPinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateToHome
    )

    private fun Ctx.changePinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            val request = ChangePinCodeRequest()
            changePinCodeUseCase.execute(request)
        },
        navigationEffect = Effect.NavigateBack
    )

    private fun Ctx.deletePinCode() = pinCodeOperation(
        pinCodeCallback = {
            deletePinCodeUseCase.execute()
        },
        navigationEffect = Effect.NavigateBack
    )

    private fun Ctx.pinCodeOperation(
        pinCodeCallback: suspend () -> Unit,
        navigationEffect: Effect
    ): Job {
        return io {
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

    private suspend fun Ctx.onSuccess(navigationEffect: Effect) {
        val params = SnackBarParams.TopFloatingParams(
            type = SnackBarType.SUCCESS,
            message = AppMessage.AppMessageText("Добро пожаловать!") // TODO
        )
        setState { copy(buttonState = SUCCESS) }
        snackBarHelper.showSnackBar(params)
        delay(3000)
        setEffect(navigationEffect)
    }

    private suspend fun Ctx.onError(message: String) {
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
