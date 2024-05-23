package validators

import model.errors.auth.PasswordError.*

/**
 * [PasswordValidator] based on regular expression.
 * Requirements:
 * - 8+ characters,
 * - min 1 uppercase,
 * - min 1 number
 * - min 1 special character
 * */
class PasswordValidatorImpl : PasswordValidator {

    private val numberRegex = ".*[0-9].*".toRegex()
    private val upperCaseRegex = ".*[A-Z].*".toRegex()
    private val specialCharRegex = ".*[@#!$%^&+=].*".toRegex()

    override fun validate(value: String) = when {
        value.length < 8 -> ShortPasswordError()
        value.matches(numberRegex).not() -> PasswordNumberError()
        value.matches(upperCaseRegex).not() -> PasswordUpperLetterError()
        value.matches(specialCharRegex).not() -> PasswordSpecialSymbolError()
        else -> null
    }
}