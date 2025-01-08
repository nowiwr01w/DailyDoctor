package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.base

import com.arkivanov.decompose.router.stack.StackNavigation
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig

interface BaseNavigator {
    /**
     * NAVIGATION STACK
     */
    val navigation: StackNavigation<MobileScreensConfig>
}
