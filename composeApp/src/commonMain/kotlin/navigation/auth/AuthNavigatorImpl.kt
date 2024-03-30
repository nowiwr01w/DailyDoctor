package navigation.auth

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.MainNavigatorImpl.AppNavigationConfig.Splash

class AuthNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): AuthNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToAuth() {
        navigation.pushNew(Splash)
    }
}