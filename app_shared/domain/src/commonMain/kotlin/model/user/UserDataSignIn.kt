package model.user

data class UserDataSignIn(
    override val phone: String,
    override val password: String
): UserData