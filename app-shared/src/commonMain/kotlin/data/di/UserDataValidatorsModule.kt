package data.di

import data.validators.EmailValidatorImpl
import data.validators.PasswordValidatorImpl
import domain.validators.EmailValidator
import domain.validators.PasswordValidator
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleUserDataValidators = DI.Module("UserDataValidatorsModule") {
    bindProvider<EmailValidator> {
        EmailValidatorImpl()
    }
    bindProvider<PasswordValidator> {
        PasswordValidatorImpl()
    }
}