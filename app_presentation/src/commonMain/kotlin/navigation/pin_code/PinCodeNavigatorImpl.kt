package navigation.pin_code

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.MainNavigatorImpl.AppNavigationConfig.PinCode

class PinCodeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): PinCodeNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToPinCode() {
        navigation.pushNew(PinCode)
    }
}