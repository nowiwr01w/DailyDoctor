package nowiwr01p.daily.doctor.app_presentation.navigation.pin_code

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.PinCode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode

class PinCodeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): PinCodeNavigator() {

    @OptIn(ExperimentalDecomposeApi::class)
    override fun navigateToPinCode(pinCodeMode: PinCodeMode) {
        navigation.pushNew(PinCode(pinCodeMode))
    }
}