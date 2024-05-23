package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding

import nowiwr01p.daily.doctor.app_presentation.navigation.base.BaseNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel

abstract class OnboardingNavigator: BaseNavigator() {
    abstract fun navigateToOnboarding(onboardingItem: OnboardingItemModel)
}