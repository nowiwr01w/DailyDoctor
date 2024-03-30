package navigation.onboarding

import com.arkivanov.decompose.ComponentContext

class OnboardingNavigatorImpl(
    context: ComponentContext,
    private val navigateToOnboardingCallback: () -> Unit
): OnboardingNavigator {
    
    override fun navigateToOnboarding() {
        navigateToOnboardingCallback()
    }
}