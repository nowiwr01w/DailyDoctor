package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Onboarding
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

class OnboardingNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): OnboardingNavigator() {
    
    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToOnboarding() {
        navigation.pushNew(Onboarding)
    }
}