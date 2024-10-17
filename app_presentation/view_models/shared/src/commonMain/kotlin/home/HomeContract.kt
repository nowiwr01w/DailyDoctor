package home

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

sealed interface HomeContract {

    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}