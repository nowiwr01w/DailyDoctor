package pin_code

import com.nowiwr01p.model.api.request.pin.ChangePinCodeRequest
import com.nowiwr01p.model.api.request.pin.CheckPinCodeRequest
import com.nowiwr01p.model.api.request.pin.CreatePinCodeRequest
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.resources.component_with_resources.screens.pin.PinScreenResources
import com.nowiwr01p.model.usecase.execute
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
import nowiwr01p.daily.doctor.local_database.domain.usecase.user.GetLocalUserUseCase
import pin_code.Event.HandleUserInput
import pin_code.PinCodeError.PasscodesDoNotMatch
import pin_code.PinCodeError.UnexpectedError
import pin_code.PinCodeState.Default
import pin_code.PinCodeState.Error
import pin_code.PinCodeState.Success
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
    private val resources: PinScreenResources,
    private val pinCodeMode: PinCodeMode,
    private val snackBarHelper: SnackBarHelper,
    private val checkPinCodeUseCase: AppCheckPinCodeUseCase,
    private val createPinCodeUseCase: AppCreatePinCodeUseCase,
    private val changePinCodeUseCase: AppChangePinCodeUseCase,
    private val deletePinCodeUseCase: AppDeletePinCodeUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase
): BaseViewModel<State, Event, Effect>(
    initialValue = State(mode = pinCodeMode)
) {
    private var previousPinCodeMode = pinCodeMode
    private var previousEnteredPinCode = ""

    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is HandleUserInput -> handleUserInput(event.operation)
        }
    }

    override suspend fun Ctx.init() {
        updateTitleAndProfileImage()
    }

    /**
     * TITLE BY MODE
     */
    private suspend fun Ctx.updateTitleAndProfileImage() = withState {
        val title = with(resources) {
            when (mode) {
                is Create -> pinCodeCreate
                is Check, Delete -> pinCodeEnter
                is Repeat -> pinCodeRepeat
                is Change -> pinCodeCreateNew
            }
        }
        val isUserProfileImageVisible = pinCodeMode !is Create && previousPinCodeMode !is Create
        setState { copy(title = title, isUserProfileImageVisible = isUserProfileImageVisible) }
    }

    /**
     * PIN CODE INPUT
     */
    private suspend fun Ctx.handleUserInput(operation: PinCodeOperation) = withState {
        val updatedPinCode = when (operation) {
            is AddDigit -> "$pinCode${operation.digit}"
            is RemoveDigit -> pinCode.dropLast(1)
        }
        setState { copy(pinCode = updatedPinCode) }
        handleModeChanging()
    }

    /**
     * MODE CHANGING
     */
    private suspend fun Ctx.handleModeChanging() = withState {
        if (pinCode.length == 4) {
            when (mode) {
                is Create, is Change -> {
                    previousPinCodeMode = mode
                    previousEnteredPinCode = pinCode
                    delay(500) // last pin color animation
                    setState { copy(mode = Repeat, pinCode = "") }
                    updateTitleAndProfileImage()
                }
                is Check -> checkPinCode(pinCode)
                is Delete -> deletePinCode()
                is Repeat -> when (previousPinCodeMode) {
                    is Create -> createPinCode(pinCode)
                    else -> changePinCode(pinCode)
                }
            }
        }
    }

    /**
     * API
     */
    private fun Ctx.createPinCode(code: String) = pinCodeOperation(
        pinCodeCallback = {
            checkArePasscodesMatch(code)
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
            checkArePasscodesMatch(code)
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
                onError(error)
            }
        }
    }

    /**
     * SUCCESS
     */
    private suspend fun Ctx.onSuccess(navigationEffect: Effect) {
        val params = SnackBarParams.TopFloatingParams(
            type = SnackBarType.SUCCESS,
            message = AppMessage.AppMessageText("Добро пожаловать!") // TODO
        )
        setState { copy(pinCodeState = Success) }
        snackBarHelper.showSnackBar(params)
        delay(3000)
        setEffect(navigationEffect)
    }

    /**
     * FAIL
     */
    private suspend fun Ctx.onError(error: Throwable) {
        val pinCodeError = when (error) {
            is PinCodeError -> error
            else -> UnexpectedError(error.message.orEmpty())
        }
        val params = SnackBarParams.TopFloatingParams(
            type = SnackBarType.ERROR,
            message = AppMessage.AppMessageText(pinCodeError.message)
        )
        snackBarHelper.showSnackBar(params)
        setState { copy(pinCodeState = Error(pinCodeError)) }
        delay(3000)
        setState { copy(mode = previousPinCodeMode, pinCodeState = Default, pinCode = "") }
        updateTitleAndProfileImage()
    }

    private fun checkArePasscodesMatch(code: String) {
        if (code != previousEnteredPinCode) {
            throw PasscodesDoNotMatch("Passcodes don't match :c")
        }
    }
}
