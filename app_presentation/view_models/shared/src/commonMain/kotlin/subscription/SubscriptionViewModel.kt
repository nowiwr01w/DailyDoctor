package subscription

import kotlinx.coroutines.CoroutineScope
import subscription.SubscriptionContract.*
import view_model.BaseViewModel

class SubscriptionViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}