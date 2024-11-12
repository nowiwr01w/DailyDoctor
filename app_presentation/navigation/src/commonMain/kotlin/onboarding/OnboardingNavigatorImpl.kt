package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Onboarding

class OnboardingNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): OnboardingNavigator() {
    
    override fun navigateToOnboarding() {
        navigation.replaceAll(Onboarding)
    }
}