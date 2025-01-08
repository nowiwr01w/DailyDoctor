package model.user

sealed interface UserData {
    val phone: String
    val password: String
}