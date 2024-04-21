package com.nowiwr01p.model.api.route

sealed interface Route {
    val route: String
}

sealed class AuthRoutes(override val route: String): Route {
    data object SingInRoute: AuthRoutes("v1/auth/signIn")
    data object SingUpRoute: AuthRoutes("v1/auth/signUp")
}