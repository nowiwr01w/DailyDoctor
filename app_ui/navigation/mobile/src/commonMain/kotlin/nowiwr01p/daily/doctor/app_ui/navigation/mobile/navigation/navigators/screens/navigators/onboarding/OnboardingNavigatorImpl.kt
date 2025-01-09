package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.onboarding

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig.Onboarding

class OnboardingNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): OnboardingNavigator {
    
    override fun navigateToOnboarding() {
        navigation.replaceAll(Onboarding)
    }
}
