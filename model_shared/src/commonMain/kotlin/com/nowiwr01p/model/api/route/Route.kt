package com.nowiwr01p.model.api.route

interface Route {
    val route: String
}

/**
 * BRAND CONFIG
 */
sealed class BrantConfigRoutes(
    override val route: String
): Route {
    data object GetBrandConfigRoute: BrantConfigRoutes("v1/config")
}

/**
 * AUTH
 */
sealed class AuthRoutes(
    override val route: String
): Route {
    data object SingInRoute: AuthRoutes("v1/auth/signIn")
    data object SingUpRoute: AuthRoutes("v1/auth/signUp")
}

/**
 * ONBOARDING
 */
sealed class OnboardingRoutes(
    override val route: String
): Route {
    data object GetOnboardingDataRoute: OnboardingRoutes("v1/onboarding")
}

/**
 * VERIFICATION
 */
sealed class VerificationRoutes(
    override val route: String
): Route {
    data object SendVerificationCodeRoute: VerificationRoutes("v1/verification/send")
    data object CheckVerificationCodeRoute: VerificationRoutes("v1/verification/check")
}

/**
 * PIN CODE
 */
sealed class PinCodeRoutes(
    override val route: String
): Route {
    data object CreatePinRoute: PinCodeRoutes("v1/pin/create")
    data object CheckPinRoute: PinCodeRoutes("v1/pin/check")
    data object ChangePinRoute: PinCodeRoutes("v1/pin/change")
    data object DeletePinRoute: PinCodeRoutes("v1/pin/delete")
}