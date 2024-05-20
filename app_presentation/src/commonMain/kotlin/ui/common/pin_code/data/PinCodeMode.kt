package ui.common.pin_code.data

enum class PinCodeMode {
    CREATE, // Registration
    REPEAT, // Registration and Change - confirmation
    CHECK, // Default behavior when open app
    CHANGE,
    DELETE // Let user log in without pin code and biometric
}