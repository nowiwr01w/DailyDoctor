package nowiwr01p.daily.doctor.database.di.repository

import nowiwr01p.daily.doctor.database.data.repository.auth.DatabaseAuthRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.pin.DatabasePinCodeRepositoryImpl
import nowiwr01p.daily.doctor.database.data.repository.verification.DatabaseVerificationRepositoryImpl
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.pin.DatabasePinCodeRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import org.koin.dsl.module

internal val moduleDatabaseRepository = module {
    /**
     * AUTH
     */
    factory<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl(userStorage = get<DatabaseUserStorage>())
    }
    /**
     * VERIFICATION
     */
    factory<DatabaseVerificationRepository> {
        DatabaseVerificationRepositoryImpl(
            userStorage = get<DatabaseUserStorage>(),
            verificationStorage = get<DatabaseVerificationStorage>()
        )
    }
    /**
     * PIN
     */
    factory<DatabasePinCodeRepository> {
        DatabasePinCodeRepositoryImpl(
            storage = get<DatabasePinCodeStorage>()
        )
    }
}