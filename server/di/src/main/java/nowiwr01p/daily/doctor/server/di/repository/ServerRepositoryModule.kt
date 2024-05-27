package nowiwr01p.daily.doctor.server.di.repository

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
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
import org.koin.dsl.module

internal val moduleServerRepository = module {
    /**
     * AUTH
     */
    /**
     * AUTH
     */
    factory<ServerAuthRepository> {
        ServerAuthRepositoryImpl(
            dispatchers = get<AppDispatchers>(),
            authRepository = get<DatabaseAuthRepository>(),
            pinCodeRepository = get<DatabasePinCodeRepository>(),
            verificationRepository = get<DatabaseVerificationRepository>(),
            generateCommonTokenUseCase = get<ServerGenerateCommonTokenUseCase>()
        )
    }
    /**
     * VERIFICATION
     */
    /**
     * VERIFICATION
     */
    factory<ServerVerificationRepository> {
        ServerVerificationRepositoryImpl(
            dispatchers = get<AppDispatchers>(),
            verificationRepository = get<DatabaseVerificationRepository>(),
            generateCommonTokenUseCase = get<ServerGenerateCommonTokenUseCase>()
        )
    }
    /**
     * PIN
     */
    /**
     * PIN
     */
    factory<ServerPinCodeRepository> {
        ServerPinCodeRepositoryImpl(
            dispatchers = get<AppDispatchers>(),
            repository = get<DatabasePinCodeRepository>()
        )
    }
}