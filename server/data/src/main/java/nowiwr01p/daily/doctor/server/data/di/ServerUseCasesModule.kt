package nowiwr01p.daily.doctor.server.data.di

import nowiwr01p.daily.doctor.server.data.usecase.ServerSignUpUseCaseImpl
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerUseCases = DI.Module("ServerUseCasesModule") {
    /**
     * USER
     */
    bindProvider<ServerSignUpUseCase> {
        ServerSignUpUseCaseImpl(repository = instance<UserRepositoryServer>())
    }
}