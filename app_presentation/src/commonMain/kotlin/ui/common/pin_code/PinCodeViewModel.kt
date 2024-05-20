package ui.common.pin_code

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.pin_code.PinCodeContract.Effect
import ui.common.pin_code.PinCodeContract.Event
import ui.common.pin_code.PinCodeContract.State
import ui.common.pin_code.data.PinCodeOperation
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCase

class PinCodeViewModel(
    scope: CoroutineScope,
    private val checkPinCodeUseCase: AppCheckPinCodeUseCase,
    private val createPinCodeUseCase: AppCreatePinCodeUseCase,
    private val changePinCodeUseCase: AppChangePinCodeUseCase
): BaseViewModel<Event, State, Effect>(scope)  {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.HandleUserInput -> handleUserInput(event.operation)
        }
    }

    private fun handleUserInput(operation: PinCodeOperation) = with(viewState.value) {
        val updatedPinCode = when (operation) {
            is PinCodeOperation.AddDigit -> "$pinCode${operation.digit}"
            is PinCodeOperation.RemoveDigit -> pinCode.dropLast(1)
        }
        setState { copy(pinCode = updatedPinCode) }
    }
}