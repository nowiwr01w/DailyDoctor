package model.user

data class UserDataSignIn(
    override val email: String,
    override val password: String
): UserData