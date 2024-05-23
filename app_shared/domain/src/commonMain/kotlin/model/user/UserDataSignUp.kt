package model.user

data class UserDataSignUp(
    override val email: String,
    override val password: String,
    val passwordRepeated: String
): UserData