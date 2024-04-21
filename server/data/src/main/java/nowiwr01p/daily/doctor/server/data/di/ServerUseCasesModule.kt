package nowiwr01p.daily.doctor.server.data.di

import nowiwr01p.daily.doctor.server.data.usecase.GetServerUserByIdUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.GetServerUsersUseCaseImpl
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUserByIdUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUsersUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerUseCases = DI.Module("ServerUseCasesModule") {
    /**
     * USER
     */
    bindProvider<GetServerUsersUseCase> {
        GetServerUsersUseCaseImpl(repository = instance<UserRepositoryServer>())
    }
    bindProvider<GetServerUserByIdUseCase> {
        GetServerUserByIdUseCaseImpl(repository = instance<UserRepositoryServer>())
    }
}