package onboarding

import view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import onboarding.OnboardingContract.*
import onboarding.data.OnboardingItem

class OnboardingViewModel(
    scope: CoroutineScope,
    private val onboardingItems: List<OnboardingItem>
) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init(event.onboardingItem)
            is Event.ShowNextOnboardingItem -> showNextOnboardingItem()
        }
    }

    private fun init(onboardingItem: OnboardingItemModel) {
        setState { copy(currentOnboardingItem = onboardingItem) }
    }

    private fun showNextOnboardingItem() = with(viewState.value) {
        val currentOnboardingItemIndex = onboardingItems.indexOf(currentOnboardingItem)
        if (currentOnboardingItemIndex != -1) {
            when {
                currentOnboardingItemIndex == onboardingItems.lastIndex -> setEffect {
                    Effect.NavigateToAuth
                }
                else -> setEffect {
                    val nextOnboardingItem = onboardingItems[currentOnboardingItemIndex + 1]
                    Effect.NavigateToNextOnboardingItem(nextOnboardingItem)
                }
            }
        }
    }
}