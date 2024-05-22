package data.di

import data.validators.EmailValidatorImpl
import data.validators.PasswordValidatorImpl
import domain.validators.EmailValidator
import domain.validators.PasswordValidator
import org.koin.dsl.module

val moduleUserDataValidators = module {
    factory<EmailValidator> {
        EmailValidatorImpl()
    }
    factory<PasswordValidator> {
        PasswordValidatorImpl()
    }
}