package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.auth

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig.Auth
import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.config.config.MobileScreensConfig.Verification

class AuthNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): AuthNavigator {

    override fun navigateToAuth() {
        navigation.replaceAll(Auth)
    }

    override fun navigateToVerification(phone: String, verificationToken: String) {
        navigation.push(Verification(phone, verificationToken))
    }
}
