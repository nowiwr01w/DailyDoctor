package nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.auth

import nowiwr01p.daily.doctor.app_ui.navigation.mobile.navigation.navigators.screens.navigators.base.BaseNavigator

interface AuthNavigator: BaseNavigator {
    fun navigateToAuth()
    fun navigateToVerification(phone: String, verificationToken: String)
}
