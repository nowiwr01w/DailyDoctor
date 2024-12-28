package onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import kotlinx.coroutines.flow.filterNot
import manager.onboarding.AppOnboardingManager
import onboarding.Effect.NavigateToAuth
import onboarding.Effect.ShowEnableNotificationsDialog
import onboarding.Effect.SlideToNextOnboardingItem
import onboarding.Event.RequestNotifications
import onboarding.Event.ShowNextOnboardingItem
import pro.respawn.flowmvi.api.PipelineContext
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class OnboardingViewModel(
    private val appOnboardingManager: AppOnboardingManager
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is RequestNotifications -> requestNotifications()
            is ShowNextOnboardingItem -> showNextOnboardingItem(event.currentOnboardingItem)
        }
    }

    override suspend fun Ctx.init() {
        getOnboardingItems()
    }

    /**
     * ONBOARDING ITEMS
     */
    private fun Ctx.getOnboardingItems() = io {
        appOnboardingManager.getOnboardingData(fromRemote = false)
            .filterNot { items -> items.isEmpty() }
            .collect { items ->
                setState { copy(onboardingItems = items) }
            }
    }

    private suspend fun Ctx.showNextOnboardingItem(currentOnboardingItem: OnboardingItem) = withState {
        val index = onboardingItems.indexOfFirst { onboardingItem ->
            onboardingItem == currentOnboardingItem
        }
        val effect = if (index != -1 && index == onboardingItems.lastIndex) {
            NavigateToAuth
        } else {
            SlideToNextOnboardingItem(index + 1)
        }
        setEffect(effect)
    }

    /**
     * NOTIFICATIONS
     */
    private suspend fun Ctx.requestNotifications() = setEffect(ShowEnableNotificationsDialog)
}
