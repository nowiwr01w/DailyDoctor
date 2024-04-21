package di

import api.auth.AuthApi
import api.auth.AuthApiImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignInUseCaseImpl
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppSignUpUseCaseImpl

val moduleAppData = DI.Module("AppDataModule") {
    /**
     * AUTH
     */
    bindSingleton<AuthApi> {
        AuthApiImpl(kodein = di)
    }
    bindProvider<AppAuthRepository> {
        AppAuthRepositoryImpl(
            api = instance<AuthApi>(),
            dispatchers = instance<AppDispatchers>()
        )
    }
    bindProvider<AppSignInUseCase> {
        AppSignInUseCaseImpl(repository = instance<AppAuthRepository>())
    }
    bindProvider<AppSignUpUseCase> {
        AppSignUpUseCaseImpl(repository = instance<AppAuthRepository>())
    }
}