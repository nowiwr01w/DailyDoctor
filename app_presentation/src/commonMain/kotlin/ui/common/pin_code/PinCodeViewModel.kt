package ui.common.pin_code

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.pin_code.PinCodeContract.Effect
import ui.common.pin_code.PinCodeContract.Event
import ui.common.pin_code.PinCodeContract.State

class PinCodeViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope)  {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}