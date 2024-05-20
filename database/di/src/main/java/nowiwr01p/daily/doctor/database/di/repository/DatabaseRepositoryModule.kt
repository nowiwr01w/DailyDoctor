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
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleDatabaseRepository = DI.Module("DatabaseRepositoryModule") {
    /**
     * AUTH
     */
    bindProvider<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl(userStorage = instance<DatabaseUserStorage>())
    }
    /**
     * VERIFICATION
     */
    bindProvider<DatabaseVerificationRepository> {
        DatabaseVerificationRepositoryImpl(
            userStorage = instance<DatabaseUserStorage>(),
            verificationStorage = instance<DatabaseVerificationStorage>()
        )
    }
    /**
     * PIN
     */
    bindProvider<DatabasePinCodeRepository> {
        DatabasePinCodeRepositoryImpl(
            storage = instance<DatabasePinCodeStorage>()
        )
    }
}