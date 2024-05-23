package validators

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.koin.dsl.module
import repository.auth.AppValidateAuthDataRepository
import repository.auth.AppValidateAuthDataRepositoryImpl
import usecase.auth.AppValidateAuthDataUseCase
import usecase.auth.AppValidateAuthDataUseCaseImpl

internal val moduleAppSharedValidators = module {
    /**
     * AUTH
     */
    factory<EmailValidator> {
        EmailValidatorImpl()
    }
    factory<PasswordValidator> {
        PasswordValidatorImpl()
    }
    factory<AppValidateAuthDataRepository> {
        AppValidateAuthDataRepositoryImpl(
            dispatchers = get<AppDispatchers>(),
            emailValidator = get<EmailValidator>(),
            passwordValidator = get<PasswordValidator>()
        )
    }
    factory<AppValidateAuthDataUseCase> {
        AppValidateAuthDataUseCaseImpl(repository = get<AppValidateAuthDataRepository>())
    }
}