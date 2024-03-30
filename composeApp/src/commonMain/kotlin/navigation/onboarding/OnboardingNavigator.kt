package navigation.onboarding

import ui.common.onboarding.data.OnboardingItem

interface OnboardingNavigator {
    fun navigateToOnboarding(onboardingItem: OnboardingItem)
}