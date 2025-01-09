package model.errors.auth

import model.errors.auth.AuthTextFieldType.PHONE

sealed class PhoneNumberError(
    override val list: List<AuthTextFieldType> = listOf(PHONE),
    override val message: String = "Аккаунт с таким номером не зарегистрирован"
): AuthError {

    data class WrongPhoneNumberFormatError(
        override val list: List<AuthTextFieldType> = listOf(PHONE),
        override val message: String = "Неверный формат номера"
    ): PhoneNumberError(list, message)
}