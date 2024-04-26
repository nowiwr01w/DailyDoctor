package com.nowiwr01p.model.api.route

sealed interface Route {
    val route: String
}

sealed class AuthRoutes(override val route: String): Route {
    data object SingInRoute: AuthRoutes("v1/auth/signIn")
    data object SingUpRoute: AuthRoutes("v1/auth/signUp")
}

sealed class PinCodeRoutes(override val route: String): Route {
    data object CreatePinRoute: AuthRoutes("v1/pin/create")
    data object ChangePinRoute: AuthRoutes("v1/pin/change")
    data object ForgotPinRoute: AuthRoutes("v1/pin/forgot")
}