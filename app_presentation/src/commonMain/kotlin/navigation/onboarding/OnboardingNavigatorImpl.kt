package navigation.onboarding

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.MainNavigatorImpl.AppNavigationConfig.Onboarding
import ui.common.onboarding.data.OnboardingItem

class OnboardingNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): OnboardingNavigator() {
    
    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToOnboarding(onboardingItem: OnboardingItem) {
        navigation.pushNew(Onboarding(onboardingItem))
    }
}