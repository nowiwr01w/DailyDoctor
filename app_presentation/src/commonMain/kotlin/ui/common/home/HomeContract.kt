package ui.common.home

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

interface HomeContract {
    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}