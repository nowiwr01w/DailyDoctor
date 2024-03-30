package navigation.onboarding

import navigation.base.BaseNavigator
import ui.common.onboarding.data.OnboardingItem

abstract class OnboardingNavigator: BaseNavigator() {
    abstract fun navigateToOnboarding(onboardingItem: OnboardingItem)
}