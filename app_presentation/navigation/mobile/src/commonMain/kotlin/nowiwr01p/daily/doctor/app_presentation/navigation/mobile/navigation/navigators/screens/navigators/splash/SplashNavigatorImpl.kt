package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.splash

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig.Splash

class SplashNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): SplashNavigator {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToSplash() {
        navigation.pushNew(Splash)
    }
}
