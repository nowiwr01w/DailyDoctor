import org.koin.dsl.module
import validators.EmailValidator
import validators.EmailValidatorImpl
import validators.PasswordValidator
import validators.PasswordValidatorImpl

val moduleAppAuthDataValidators = module {
    factory<EmailValidator> {
        EmailValidatorImpl()
    }
    factory<PasswordValidator> {
        PasswordValidatorImpl()
    }
}