package nowiwr01p.daily.doctor.app_presentation.navigation.auth

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Auth
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Verification

class AuthNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): AuthNavigator() {

    override fun navigateToAuth() {
        navigation.replaceAll(Auth)
    }

    override fun navigateToVerification(phone: String, verificationToken: String) {
        navigation.push(Verification(phone, verificationToken))
    }
}