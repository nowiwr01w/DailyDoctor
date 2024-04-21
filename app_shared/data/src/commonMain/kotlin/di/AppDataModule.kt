package di

import api.UserApiImpl
import api.user.UserApi
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import repository.user.AppUserRepository
import repository.user.AppUserRepositoryImpl
import usecase.user.LoadAppUserByIdUseCase
import usecase.user.LoadAppUserByIdUseCaseImpl
import usecase.user.LoadAppUsersUseCase
import usecase.user.LoadAppUsersUseCaseImpl

val moduleAppData = DI.Module("AppDataModule") {
    /**
     * USER
     */
    bindSingleton<UserApi> {
        UserApiImpl(kodein = di)
    }
    bindProvider<AppUserRepository> {
        AppUserRepositoryImpl(
            api = instance<UserApi>(),
            dispatchers = instance<AppDispatchers>()
        )
    }
    bindProvider<LoadAppUsersUseCase> {
        LoadAppUsersUseCaseImpl(repository = instance<AppUserRepository>())
    }
    bindProvider<LoadAppUserByIdUseCase> {
        LoadAppUserByIdUseCaseImpl(repository = instance<AppUserRepository>())
    }
}