package nowiwr01p.daily.doctor.app_presentation.navigation.home

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.*

class HomeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): HomeNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToHome() {
        navigation.pushNew(Home)
    }
}