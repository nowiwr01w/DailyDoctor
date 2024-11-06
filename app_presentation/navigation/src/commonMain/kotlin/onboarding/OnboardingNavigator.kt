package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding

import nowiwr01p.daily.doctor.app_presentation.navigation.base.BaseNavigator
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

abstract class OnboardingNavigator: BaseNavigator() {
    abstract fun navigateToOnboarding()
}