package domain.repository.auth.data.errors

interface AuthError {
    val list: List<AuthTextFieldType>
    val message: String
}