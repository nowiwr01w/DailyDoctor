package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.pin_code

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceAll
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.config.MobileScreensConfig.PinCode
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode

class PinCodeNavigatorImpl(
    override val navigation: StackNavigation<MobileScreensConfig>
): PinCodeNavigator {

    override fun navigateToPinCode(pinCodeMode: PinCodeMode) {
        navigation.replaceAll(PinCode(pinCodeMode))
    }
}
