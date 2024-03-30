package navigation.onboarding

import com.arkivanov.decompose.ComponentContext
import ui.common.onboarding.data.OnboardingItem

class OnboardingNavigatorImpl(
    context: ComponentContext,
    private val navigateToOnboardingCallback: (onboardingItem: OnboardingItem) -> Unit
): OnboardingNavigator {
    
    override fun navigateToOnboarding(onboardingItem: OnboardingItem) {
        navigateToOnboardingCallback(onboardingItem)
    }
}