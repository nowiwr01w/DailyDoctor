package nowiwr01p.daily.doctor.app_presentation.navigation.auth

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Auth
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.Verification

@OptIn(ExperimentalDecomposeApi::class)
class AuthNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): AuthNavigator() {

    override fun navigateToAuth() {
        navigation.pushNew(Auth)
    }

    override fun navigateToVerification(email: String, verificationToken: String) {
        navigation.pushNew(Verification(email, verificationToken))
    }
}