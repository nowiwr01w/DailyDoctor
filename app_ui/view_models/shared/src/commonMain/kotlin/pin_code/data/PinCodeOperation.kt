package pin_code.data

sealed interface PinCodeOperation {
    data object RemoveDigit: PinCodeOperation
    data class AddDigit(val digit: String): PinCodeOperation
}