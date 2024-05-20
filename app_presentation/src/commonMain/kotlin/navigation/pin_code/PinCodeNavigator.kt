package navigation.pin_code

import navigation.base.BaseNavigator
import ui.common.pin_code.data.PinCodeMode

abstract class PinCodeNavigator: BaseNavigator() {
    abstract fun navigateToPinCode(pinCodeMode: PinCodeMode)
}