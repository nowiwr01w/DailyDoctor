package model.errors.auth

interface AuthError {
    val list: List<AuthTextFieldType>
    val message: String
}