package domain.di

import domain.repository.auth.AuthRepository
import domain.repository.auth.ValidateAuthDataRepository
import domain.usecase.auth.SignInUseCase
import domain.usecase.auth.SignUpUseCase
import domain.usecase.auth.ValidateAuthDataUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleDomain = DI.Module("DomainModule") {
    /**
     * AUTH
     */
    bindProvider {
        ValidateAuthDataUseCase(repository = instance<ValidateAuthDataRepository>())
    }
    bindProvider {
        SignInUseCase(repository = instance<AuthRepository>())
    }
    bindProvider {
        SignUpUseCase(repository = instance<AuthRepository>())
    }
}