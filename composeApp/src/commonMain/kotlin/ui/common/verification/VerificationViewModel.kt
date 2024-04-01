package ui.common.verification

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.verification.VerificationContract.Effect
import ui.common.verification.VerificationContract.Event
import ui.common.verification.VerificationContract.State

class VerificationViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}