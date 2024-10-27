package nowiwr01p.daily.doctor.server.main.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import nowiwr01p.daily.doctor.server.routes.auth.AuthRouting
import nowiwr01p.daily.doctor.server.routes.pin_code.PinCodeRouting
import nowiwr01p.daily.doctor.server.routes.verification.VerificationRouting
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authRouting by inject<AuthRouting>()
    val verificationRouting by inject<VerificationRouting>()
    val pinCodeRouting by inject<PinCodeRouting>()
    routing {
        configureAuthRouting(authRouting)
        configureVerificationCodeRouting(verificationRouting)
        configurePinCodeRouting(pinCodeRouting)
    }
}

private fun Route.configureAuthRouting(authRouting: AuthRouting) {
    authRouting.signIn(this)
    authRouting.signUp(this)
}

private fun Route.configureVerificationCodeRouting(verificationRouting: VerificationRouting) {
    verificationRouting.sendVerificationCode(this)
    verificationRouting.checkVerificationCode(this)
}

private fun Route.configurePinCodeRouting(pinCodeRouting: PinCodeRouting) {
    pinCodeRouting.createPinCode(this)
    pinCodeRouting.checkPinCode(this)
    pinCodeRouting.changePinCode(this)
    pinCodeRouting.deletePinCode(this)
}