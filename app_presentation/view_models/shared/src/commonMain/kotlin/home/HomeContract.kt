package home

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

sealed interface Event: BaseEvent {

}

sealed interface State: BaseState {
    data object Default: State
}

sealed interface Effect: BaseEffect {

}

interface Listener {

}
