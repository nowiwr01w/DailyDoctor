package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.home

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig.Home

class HomeNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): HomeNavigator {

    override fun navigateToHome() {
        navigation.replaceAll(Home)
    }
}
