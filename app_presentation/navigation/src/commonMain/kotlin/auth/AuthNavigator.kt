package nowiwr01p.daily.doctor.app_presentation.navigation.auth

import nowiwr01p.daily.doctor.app_presentation.navigation.base.BaseNavigator

abstract class AuthNavigator: BaseNavigator() {
    abstract fun navigateToAuth()
    abstract fun navigateToVerification(email: String, verificationToken: String)
}