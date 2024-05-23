package model.errors.auth

data class EmptyFieldsError(
    val emptyFields: List<AuthTextFieldType>
): AuthError {
    override val list = emptyFields
    override val message = "У вас ${emptyFields.size} " + when (emptyFields.size) {
        1 -> "незаполненное поле" // TODO: Do with plurals
        else -> "незаполненных поля"
    }
}