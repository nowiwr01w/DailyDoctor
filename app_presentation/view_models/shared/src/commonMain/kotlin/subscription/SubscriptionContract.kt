package subscription

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState

interface SubscriptionContract {

    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}