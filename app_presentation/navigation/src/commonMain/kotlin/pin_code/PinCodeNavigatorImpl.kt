package nowiwr01p.daily.doctor.app_presentation.navigation.pin_code

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.MainNavigatorImpl.AppNavigationConfig.PinCode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode

class PinCodeNavigatorImpl(
    override val navigation: StackNavigation<AppNavigationConfig>
): PinCodeNavigator() {

    override fun navigateToPinCode(pinCodeMode: PinCodeMode) {
        navigation.replaceAll(PinCode(pinCodeMode))
    }
}