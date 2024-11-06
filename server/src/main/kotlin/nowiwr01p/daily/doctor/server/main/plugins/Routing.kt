package nowiwr01p.daily.doctor.server.main.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import nowiwr01p.daily.doctor.server.routes.auth.AuthRouting
import nowiwr01p.daily.doctor.server.routes.brand_config.BrandConfigRouting
import nowiwr01p.daily.doctor.server.routes.onboarding.OnboardingRouting
import nowiwr01p.daily.doctor.server.routes.pin_code.PinCodeRouting
import nowiwr01p.daily.doctor.server.routes.verification.VerificationRouting
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val brandConfigRouting by inject<BrandConfigRouting>()
    val onboardingRouting by inject<OnboardingRouting>()
    val authRouting by inject<AuthRouting>()
    val verificationRouting by inject<VerificationRouting>()
    val pinCodeRouting by inject<PinCodeRouting>()
    routing {
        configureBrandConfigRouting(brandConfigRouting)
        configureOnboardingRouting(onboardingRouting)
        configureAuthRouting(authRouting)
        configureVerificationCodeRouting(verificationRouting)
        configurePinCodeRouting(pinCodeRouting)
    }
}

/**
 * BRAND CONFIG
 */
private fun Route.configureBrandConfigRouting(brandConfigRouting: BrandConfigRouting) {
    brandConfigRouting.getBrandConfig(this)
}

/**
 * ONBOARDING
 */
private fun Route.configureOnboardingRouting(onboardingRouting: OnboardingRouting) {
    onboardingRouting.getOnboardingData(this)
}

/**
 * AUTH
 */
private fun Route.configureAuthRouting(authRouting: AuthRouting) {
    authRouting.signIn(this)
    authRouting.signUp(this)
}

/**
 * VERIFICATION CODE
 */
private fun Route.configureVerificationCodeRouting(verificationRouting: VerificationRouting) {
    verificationRouting.sendVerificationCode(this)
    verificationRouting.checkVerificationCode(this)
}

/**
 * PIN CODE
 */
private fun Route.configurePinCodeRouting(pinCodeRouting: PinCodeRouting) {
    pinCodeRouting.createPinCode(this)
    pinCodeRouting.checkPinCode(this)
    pinCodeRouting.changePinCode(this)
    pinCodeRouting.deletePinCode(this)
}