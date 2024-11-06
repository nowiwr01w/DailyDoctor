package nowiwr01p.daily.doctor.server.di.usecase

import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignInUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignUpUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.brand_config.ServerGetBrandConfigUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.onboarding.ServerGetOnboardingDataUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerChangePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerCheckPinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerCreatePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerDeletePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerCheckVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerDeleteExpiredVerificationCodesUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerSendVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.brand_config.ServerBrandConfigRepository
import nowiwr01p.daily.doctor.server.domain.repository.onboarding.ServerOnboardingRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.brand_config.ServerGetBrandConfigUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.onboarding.ServerGetOnboardingDataUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import org.koin.dsl.module

internal val moduleServerUseCases = module {
    /**
     * BRAND CONFIG
     */
    factory<ServerGetBrandConfigUseCase> {
        ServerGetBrandConfigUseCaseImpl(repository = get<ServerBrandConfigRepository>())
    }
    /**
     * ONBOARDING
     */
    factory<ServerGetOnboardingDataUseCase> {
        ServerGetOnboardingDataUseCaseImpl(repository = get<ServerOnboardingRepository>())
    }
    /**
     * AUTH
     */
    factory<ServerSignUpUseCase> {
        ServerSignUpUseCaseImpl(repository = get<ServerAuthRepository>())
    }
    factory<ServerSignInUseCase> {
        ServerSignInUseCaseImpl(repository = get<ServerAuthRepository>())
    }
    /**
     * VERIFICATION
     */
    factory<ServerSendVerificationCodeUseCase> {
        ServerSendVerificationCodeUseCaseImpl(
            repository = get<ServerVerificationRepository>()
        )
    }
    factory<ServerCheckVerificationCodeUseCase> {
        ServerCheckVerificationCodeUseCaseImpl(
            repository = get<ServerVerificationRepository>()
        )
    }
    factory<ServerDeleteExpiredVerificationCodesUseCase> {
        ServerDeleteExpiredVerificationCodesUseCaseImpl(
            repository = get<ServerVerificationRepository>()
        )
    }
    /**
     * PIN
     */
    factory<ServerChangePinCodeUseCase> {
        ServerChangePinCodeUseCaseImpl(
            repository = get<ServerPinCodeRepository>()
        )
    }
    factory<ServerCreatePinCodeUseCase> {
        ServerCreatePinCodeUseCaseImpl(
            repository = get<ServerPinCodeRepository>()
        )
    }
    factory<ServerDeletePinCodeUseCase> {
        ServerDeletePinCodeUseCaseImpl(
            repository = get<ServerPinCodeRepository>()
        )
    }
    factory<ServerCheckPinCodeUseCase> {
        ServerCheckPinCodeUseCaseImpl(
            repository = get<ServerPinCodeRepository>()
        )
    }
}