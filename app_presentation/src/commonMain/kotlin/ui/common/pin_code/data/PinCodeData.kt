package ui.common.pin_code.data

import dailydoctor.app_presentation.generated.resources.Res
import dailydoctor.app_presentation.generated.resources.ic_delete
import dailydoctor.app_presentation.generated.resources.ic_fingerprint
import org.jetbrains.compose.resources.DrawableResource
import ui.common.pin_code.data.PinCodeData.PinCodeDigit
import ui.common.pin_code.data.PinCodeData.PinCodeIcon

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

internal val pinCodeData = listOf(
    PinCodeDigit("1"),
    PinCodeDigit("2"),
    PinCodeDigit("3"),
    PinCodeDigit("4"),
    PinCodeDigit("5"),
    PinCodeDigit("6"),
    PinCodeDigit("7"),
    PinCodeDigit("8"),
    PinCodeDigit("9"),
    PinCodeIcon(
        type = PinCodeIconType.BIOMETRIC,
        value = Res.drawable.ic_fingerprint
    ),
    PinCodeDigit("0"),
    PinCodeIcon(
        type = PinCodeIconType.REMOVE_DIGIT,
        value = Res.drawable.ic_delete
    )
)