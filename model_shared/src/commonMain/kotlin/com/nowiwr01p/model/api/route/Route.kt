package com.nowiwr01p.model.api.route

sealed interface Route {
    val route: String
}

/**
 * AUTH
 */
sealed class AuthRoutes(override val route: String): Route {
    data object SingInRoute: AuthRoutes("v1/auth/signIn")
    data object SingUpRoute: AuthRoutes("v1/auth/signUp")
}

/**
 * VERIFICATION
 */
sealed class VerificationRoutes(override val route: String): Route {
    data object SendVerificationCodeRoute: VerificationRoutes("v1/verification/send")
    data object CheckVerificationCodeRoute: VerificationRoutes("v1/verification/check")
}

/**
 * PIN CODE
 */
sealed class PinCodeRoutes(override val route: String): Route {
    data object CreatePinRoute: AuthRoutes("v1/pin/create")
    data object CheckPinRoute: AuthRoutes("v1/pin/check")
    data object ChangePinRoute: AuthRoutes("v1/pin/change")
    data object DeletePinRoute: AuthRoutes("v1/pin/delete")
}