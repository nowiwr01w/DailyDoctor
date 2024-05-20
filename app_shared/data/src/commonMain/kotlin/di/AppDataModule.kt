package di

import api.auth.AuthApi
import api.auth.AuthApiImpl
import api.verification.VerificationApi
import api.verification.VerificationApiImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignInUseCaseImpl
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppSignUpUseCaseImpl
import usecase.verification.AppCheckVerificationCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCaseImpl
import usecase.verification.AppSendVerificationCodeUseCase
import usecase.verification.AppSendVerificationCodeUseCaseImpl

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
    /**
     * VERIFICATION
     */
    bindSingleton<VerificationApi> {
        VerificationApiImpl(kodein = di)
    }
    bindProvider<AppVerificationRepository> {
        AppVerificationRepositoryImpl(
            api = instance<VerificationApi>(),
            dispatchers = instance<AppDispatchers>()
        )
    }
    bindProvider<AppSendVerificationCodeUseCase> {
        AppSendVerificationCodeUseCaseImpl(repository = instance<AppVerificationRepository>())
    }
    bindProvider<AppCheckVerificationCodeUseCase> {
        AppCheckVerificationCodeUseCaseImpl(repository = instance<AppVerificationRepository>())
    }
}