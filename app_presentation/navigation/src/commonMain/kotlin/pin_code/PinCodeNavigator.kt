package nowiwr01p.daily.doctor.app_presentation.navigation.pin_code

import nowiwr01p.daily.doctor.app_presentation.navigation.base.BaseNavigator
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode

abstract class PinCodeNavigator: BaseNavigator() {
    abstract fun navigateToPinCode(pinCodeMode: PinCodeMode)
}