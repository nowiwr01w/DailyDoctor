package nowiwr01p.daily.doctor.server.di.repository

import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.data.repository.auth.ServerAuthRepositoryImpl
import nowiwr01p.daily.doctor.server.data.repository.pin.ServerPinCodeRepositoryImpl
import nowiwr01p.daily.doctor.server.data.repository.verification.ServerVerificationRepositoryImpl
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.pin.ServerPinCodeRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import nowiwr01p.daily.doctor.server.token.common.usecase.ServerGenerateCommonTokenUseCase
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase
import org.koin.dsl.module

internal val moduleServerRepository = module {
    /**
     * AUTH
     */
    factory<ServerAuthRepository> {
        ServerAuthRepositoryImpl(
            authRepository = get<DatabaseAuthRepository>(),
            pinCodeRepository = get<DatabasePinCodeRepository>(),
            verificationRepository = get<ServerVerificationRepository>(),
        )
    }
    /**
     * VERIFICATION
     */
    factory<ServerVerificationRepository> {
        ServerVerificationRepositoryImpl(
            databaseVerificationRepository = get<DatabaseVerificationRepository>(),
            tgSendVerificationCodeUseCase = get<TgSendVerificationCodeUseCase>(),
            verificationCodeGenerator = get<VerificationCodeGenerator>(),
            generateCommonTokenUseCase = get<ServerGenerateCommonTokenUseCase>()
        )
    }
    /**
     * PIN
     */
    factory<ServerPinCodeRepository> {
        ServerPinCodeRepositoryImpl(
            repository = get<DatabasePinCodeRepository>(),
            generateCommonTokenUseCase = get<ServerGenerateCommonTokenUseCase>()
        )
    }
}