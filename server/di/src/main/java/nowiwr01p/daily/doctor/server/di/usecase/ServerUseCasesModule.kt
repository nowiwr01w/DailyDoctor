package nowiwr01p.daily.doctor.server.di.usecase

import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignInUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignUpUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerChangePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerCreatePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerDeletePinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.pin.ServerCheckPinCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerCheckVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerDeleteExpiredVerificationCodesUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerSendVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleServerUseCases = DI.Module("ServerUseCasesModule") {
    /**
     * AUTH
     */
    bindProvider<ServerSignUpUseCase> {
        ServerSignUpUseCaseImpl(repository = instance<ServerAuthRepository>())
    }
    bindProvider<ServerSignInUseCase> {
        ServerSignInUseCaseImpl(repository = instance<ServerAuthRepository>())
    }
    /**
     * VERIFICATION
     */
    bindProvider<ServerSendVerificationCodeUseCase> {
        ServerSendVerificationCodeUseCaseImpl(
            repository = instance<ServerVerificationRepository>()
        )
    }
    bindProvider<ServerCheckVerificationCodeUseCase> {
        ServerCheckVerificationCodeUseCaseImpl(
            repository = instance<ServerVerificationRepository>()
        )
    }
    bindProvider<ServerDeleteExpiredVerificationCodesUseCase> {
        ServerDeleteExpiredVerificationCodesUseCaseImpl(
            repository = instance<ServerVerificationRepository>()
        )
    }
    /**
     * PIN
     */
    bindProvider<ServerChangePinCodeUseCase> {
        ServerChangePinCodeUseCaseImpl(
            repository = instance<ServerPinCodeRepository>()
        )
    }
    bindProvider<ServerCreatePinCodeUseCase> {
        ServerCreatePinCodeUseCaseImpl(
            repository = instance<ServerPinCodeRepository>()
        )
    }
    bindProvider<ServerDeletePinCodeUseCase> {
        ServerDeletePinCodeUseCaseImpl(
            repository = instance<ServerPinCodeRepository>()
        )
    }
    bindProvider<ServerCheckPinCodeUseCase> {
        ServerCheckPinCodeUseCaseImpl(
            repository = instance<ServerPinCodeRepository>()
        )
    }
}