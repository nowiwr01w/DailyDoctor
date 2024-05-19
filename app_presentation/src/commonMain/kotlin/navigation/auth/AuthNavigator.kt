package navigation.auth

import navigation.base.BaseNavigator

abstract class AuthNavigator: BaseNavigator() {
    abstract fun navigateToAuth()
    abstract fun navigateToVerification(email: String, verificationToken: String)
}