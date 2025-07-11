package validators

import model.errors.auth.PhoneNumberError.*

class EmailValidatorImpl : EmailValidator {

    override fun validate(value: String) = when {
        EMAIL_REGEX.toRegex().matches(value).not() -> WrongPhoneNumberFormatError()
        else -> null
    }

    private companion object {
        const val EMAIL_REGEX =  """^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"""
    }
}