package nowiwr01p.daily.doctor.app_presentation.navigation.splash

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Splash

class SplashNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): SplashNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToSplash() {
        navigation.pushNew(Splash)
    }
}