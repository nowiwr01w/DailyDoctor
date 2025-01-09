package model.user

data class UserDataSignUp(
    override val phone: String,
    override val password: String,
    val passwordRepeated: String
): UserData