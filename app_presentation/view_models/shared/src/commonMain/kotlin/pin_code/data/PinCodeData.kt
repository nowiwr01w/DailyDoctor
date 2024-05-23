package pin_code.data

import org.jetbrains.compose.resources.DrawableResource
import pin_code.data.PinCodeData.PinCodeDigit
import pin_code.data.PinCodeData.PinCodeIcon

sealed class PinCodeData(
    open val value: Any
) {
    data class PinCodeDigit(
        override val value: String
    ): PinCodeData(value)

    data class PinCodeIcon(
        val type: PinCodeIconType,
        override val value: DrawableResource
    ): PinCodeData(value)
}

enum class PinCodeIconType {
    BIOMETRIC,
    REMOVE_DIGIT
}