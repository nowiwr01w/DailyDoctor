package domain.model.user

sealed interface UserData {
    val email: String
    val password: String
}