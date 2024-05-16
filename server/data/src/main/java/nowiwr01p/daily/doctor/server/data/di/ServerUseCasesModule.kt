package nowiwr01p.daily.doctor.server.data.di

import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignInUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.auth.ServerSignUpUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerCheckVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.data.usecase.verification.ServerSendVerificationCodeUseCaseImpl
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerUseCases = DI.Module("ServerUseCasesModule") {
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
        ServerSendVerificationCodeUseCaseImpl(repository = instance<ServerVerificationRepository>())
    }
    bindProvider<ServerCheckVerificationCodeUseCase> {
        ServerCheckVerificationCodeUseCaseImpl(repository = instance<ServerVerificationRepository>())
    }
}