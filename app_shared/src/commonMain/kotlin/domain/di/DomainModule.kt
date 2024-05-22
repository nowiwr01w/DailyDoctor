package domain.di

import domain.repository.auth.AuthRepository
import domain.repository.auth.ValidateAuthDataRepository
import domain.usecase.auth.SignInUseCase
import domain.usecase.auth.SignUpUseCase
import domain.usecase.auth.ValidateAuthDataUseCase
import org.koin.dsl.module

val moduleDomainApp = module {
    /**
     * AUTH
     */
    factory {
        ValidateAuthDataUseCase(repository = get<ValidateAuthDataRepository>())
    }
    factory {
        SignInUseCase(repository = get<AuthRepository>())
    }
    factory {
        SignUpUseCase(repository = get<AuthRepository>())
    }
}