package onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNot
import onboarding.OnboardingContract.*
import manager.onboarding.AppOnboardingManager
import onboarding.OnboardingContract.Effect.*
import onboarding.OnboardingContract.Event.*

class OnboardingViewModel(
    scope: CoroutineScope,
    private val appOnboardingManager: AppOnboardingManager
) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is RequestNotifications -> requestNotifications()
            is ShowNextOnboardingItem -> showNextOnboardingItem(event.currentOnboardingItem)
        }
    }

    private fun init() {
        getOnboardingItems()
    }

    private fun getOnboardingItems() = hide {
        appOnboardingManager.getOnboardingData(fromRemote = false)
            .filterNot { items -> items.isEmpty() }
            .collect { items ->
                setState { copy(onboardingItems = items) }
            }
    }

    private fun showNextOnboardingItem(currentOnboardingItem: OnboardingItem) {
        with(viewState.value) {
            val index = onboardingItems.indexOfFirst { onboardingItem ->
                onboardingItem == currentOnboardingItem
            }
            val effect = if (index != -1 && index == onboardingItems.lastIndex) {
                NavigateToAuth
            } else {
                SlideToNextOnboardingItem(index + 1)
            }
            setEffect { effect }
        }
    }

    private fun requestNotifications() = setEffect { ShowEnableNotificationsDialog }
}