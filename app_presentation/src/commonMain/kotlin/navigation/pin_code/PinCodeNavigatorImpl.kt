package navigation.pin_code

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import navigation.MainNavigatorImpl.AppNavigationConfig
import navigation.MainNavigatorImpl.AppNavigationConfig.PinCode
import ui.common.pin_code.data.PinCodeMode

class PinCodeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): PinCodeNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToPinCode(pinCodeMode: PinCodeMode) {
        navigation.pushNew(PinCode(pinCodeMode))
    }
}