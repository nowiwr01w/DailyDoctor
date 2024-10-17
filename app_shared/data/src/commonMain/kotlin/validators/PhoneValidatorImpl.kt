package validators

import model.errors.auth.PhoneNumberError.WrongPhoneNumberFormatError

class PhoneValidatorImpl: PhoneValidator {

    override fun validate(value: String) = when {
        value.matches(e164phoneFormat).not() -> WrongPhoneNumberFormatError()
        else -> null
    }

    private companion object {
        val e164phoneFormat = "^\\+[1-9][0-9]{1,14}$".toRegex()
    }
}