package nowiwr01p.daily.doctor.server.main.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import nowiwr01p.daily.doctor.server.routes.auth.AuthRouting
import nowiwr01p.daily.doctor.server.routes.pin_code.PinCodeRouting
import nowiwr01p.daily.doctor.server.routes.verification.VerificationRouting
import org.koin.ktor.ext.inject

fun Application.configureRouting() = routing {
    configureAuthRouting()
    configureVerificationCodeRouting()
    configurePinCodeRouting()
}

private fun Route.configureAuthRouting() {
    val authRouting by inject<AuthRouting>()
    authRouting.signIn(this)
    authRouting.signUp(this)
}

private fun Route.configureVerificationCodeRouting() {
    val verificationRouting by inject<VerificationRouting>()
    verificationRouting.sendVerificationCode(this)
    verificationRouting.checkVerificationCode(this)
}

private fun Route.configurePinCodeRouting() {
    val pinCodeRouting by inject<PinCodeRouting>()
    pinCodeRouting.createPinCode(this)
    pinCodeRouting.checkPinCode(this)
    pinCodeRouting.changePinCode(this)
    pinCodeRouting.deletePinCode(this)
}