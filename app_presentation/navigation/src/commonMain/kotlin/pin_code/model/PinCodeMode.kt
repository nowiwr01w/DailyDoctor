package nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model

enum class PinCodeMode {
    CREATE, // Registration
    REPEAT, // Registration and Change - confirmation
    CHECK, // Default behavior when open app
    CHANGE,
    DELETE // Let user log in without pin code and biometric
}