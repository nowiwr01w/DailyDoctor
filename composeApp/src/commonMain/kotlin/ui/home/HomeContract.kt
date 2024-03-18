package ui.home

import base.BaseEffect
import base.BaseEvent
import base.BaseState

interface HomeContract {
    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}