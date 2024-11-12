package nowiwr01p.daily.doctor.app_presentation.navigation.home

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Home

class HomeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): HomeNavigator() {

    override fun navigateToHome() {
        navigation.replaceAll(Home)
    }
}