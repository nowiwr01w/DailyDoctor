package nowiwr01p.daily.doctor.app_ui.navigation.model.pin

import kotlinx.serialization.Serializable

@Serializable
sealed class PinCodeMode(
    open val pinCodeToken: String
) {
    @Serializable
    data class Create(val token: String): PinCodeMode(token)  // Registration

    @Serializable
    data object Repeat: PinCodeMode("")  // Registration and Change - confirmation

    @Serializable
    data class Check(val token: String): PinCodeMode(token)  // Default behavior when open app

    @Serializable
    data object Change: PinCodeMode("")

    @Serializable
    data object Delete: PinCodeMode("")  // Let user log in without pin code and biometric
}
