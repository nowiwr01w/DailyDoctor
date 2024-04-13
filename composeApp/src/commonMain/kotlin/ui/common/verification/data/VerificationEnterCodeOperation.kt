package ui.common.verification.data

sealed class VerificationEnterCodeOperation(
    open val index: Int,
    open val digit: String
) {
    data class RemoveDigit(
        override val index: Int,
        override val digit: String = ""
    ): VerificationEnterCodeOperation(index, digit)

    data class SetDigit(
        override val index: Int,
        override val digit: String
    ): VerificationEnterCodeOperation(index, digit)
}