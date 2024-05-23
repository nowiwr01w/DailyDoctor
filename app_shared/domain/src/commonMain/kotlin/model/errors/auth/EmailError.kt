package model.errors.auth

import model.errors.auth.AuthTextFieldType.EMAIL

sealed class EmailError(
    override val list: List<AuthTextFieldType> = listOf(EMAIL),
    override val message: String = "Аккаунт с такой почтой не зарегистрирован"
): AuthError {

    data class WrongEmailFormatError(
        override val list: List<AuthTextFieldType> = listOf(EMAIL),
        override val message: String = "Неверный формат электронной почты"
    ): EmailError(list, message)
}