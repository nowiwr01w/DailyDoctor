package domain.repository.auth.data.errors

import domain.repository.auth.data.errors.AuthTextFieldType.EMAIL
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD_CONFIRMATION

data class EmptyFieldsError(
    val emptyFields: List<AuthTextFieldType>
): AuthError {
    override val list = emptyFields
    override val message = "У вас ${emptyFields.size} незаполненных поля. " +
            "Заполните поля ${emptyFields.getFieldNames()}"
}

fun List<AuthTextFieldType>.getFieldNames() = mutableListOf<String>().apply {
    this@getFieldNames.forEach {
        when (it) {
            EMAIL -> add("email")
            PASSWORD -> add("password")
            PASSWORD_CONFIRMATION -> add("password_repeat")
        }
    }
}