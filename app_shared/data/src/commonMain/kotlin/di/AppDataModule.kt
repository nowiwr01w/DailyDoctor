package di

import api.auth.AuthApi
import api.auth.AuthApiImpl
import api.pin.PinApi
import api.pin.PinApiImpl
import api.verification.VerificationApi
import api.verification.VerificationApiImpl
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import org.koin.dsl.module
import repository.auth.AppAuthRepository
import repository.auth.AppAuthRepositoryImpl
import repository.pin.AppPinRepository
import repository.pin.AppPinRepositoryImpl
import repository.verification.AppVerificationRepository
import repository.verification.AppVerificationRepositoryImpl
import usecase.auth.AppSignInUseCase
import usecase.auth.AppSignInUseCaseImpl
import usecase.auth.AppSignUpUseCase
import usecase.auth.AppSignUpUseCaseImpl
import usecase.pin.AppChangePinCodeUseCase
import usecase.pin.AppChangePinCodeUseCaseImpl
import usecase.pin.AppCheckPinCodeUseCase
import usecase.pin.AppCheckPinCodeUseCaseImpl
import usecase.pin.AppCreatePinCodeUseCase
import usecase.pin.AppCreatePinCodeUseCaseImpl
import usecase.pin.AppDeletePinCodeUseCase
import usecase.pin.AppDeletePinCodeUseCaseImpl
import usecase.verification.AppCheckVerificationCodeUseCase
import usecase.verification.AppCheckVerificationCodeUseCaseImpl
import usecase.verification.AppSendVerificationCodeUseCase
import usecase.verification.AppSendVerificationCodeUseCaseImpl

val moduleDataShared = module {
    /**
     * AUTH
     */
    single<AuthApi> {
        AuthApiImpl()
    }
    factory<AppAuthRepository> {
        AppAuthRepositoryImpl(
            api = get<AuthApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
    factory<AppSignInUseCase> {
        AppSignInUseCaseImpl(repository = get<AppAuthRepository>())
    }
    factory<AppSignUpUseCase> {
        AppSignUpUseCaseImpl(repository = get<AppAuthRepository>())
    }
    /**
     * VERIFICATION
     */
    single<VerificationApi> {
        VerificationApiImpl()
    }
    factory<AppVerificationRepository> {
        AppVerificationRepositoryImpl(
            api = get<VerificationApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
    factory<AppSendVerificationCodeUseCase> {
        AppSendVerificationCodeUseCaseImpl(repository = get<AppVerificationRepository>())
    }
    factory<AppCheckVerificationCodeUseCase> {
        AppCheckVerificationCodeUseCaseImpl(repository = get<AppVerificationRepository>())
    }
    /**
     * PIN
     */
    single<PinApi> {
        PinApiImpl()
    }
    factory<AppPinRepository> {
        AppPinRepositoryImpl(
            api = get<PinApi>(),
            dispatchers = get<AppDispatchers>()
        )
    }
    factory<AppChangePinCodeUseCase> {
        AppChangePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppCreatePinCodeUseCase> {
        AppCreatePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppDeletePinCodeUseCase> {
        AppDeletePinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
    factory<AppCheckPinCodeUseCase> {
        AppCheckPinCodeUseCaseImpl(
            repository = get<AppPinRepository>()
        )
    }
}