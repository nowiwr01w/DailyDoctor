package navigation.auth

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.MainNavigatorImpl.AppNavigationConfig.Auth
import navigation.MainNavigatorImpl.AppNavigationConfig.Verification

@OptIn(ExperimentalDecomposeApi::class)
class AuthNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): AuthNavigator() {

    override fun navigateToAuth() {
        navigation.pushNew(Auth)
    }

    override fun navigateToVerification() {
        navigation.pushNew(Verification)
    }
}