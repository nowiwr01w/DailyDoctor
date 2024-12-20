package nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.pin_code

import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.navigators.screens.navigators.base.BaseNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode

interface PinCodeNavigator: BaseNavigator {
    fun navigateToPinCode(pinCodeMode: PinCodeMode)
}
