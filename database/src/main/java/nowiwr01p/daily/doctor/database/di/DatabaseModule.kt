package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepositoryImpl
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepositoryImpl
import nowiwr01p.daily.doctor.database.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.storage.user.DatabaseUserStorageImpl
import nowiwr01p.daily.doctor.database.storage.verification.DatabaseVerificationStorage
import nowiwr01p.daily.doctor.database.storage.verification.DatabaseVerificationStorageImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleDatabase = DI.Module("DatabaseModule") {
    /**
     * USER
     */
    bindProvider<DatabaseUserStorage> {
        DatabaseUserStorageImpl()
    }
    bindProvider<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl(userStorage = instance<DatabaseUserStorage>())
    }
    /**
     * VERIFICATION
     */
    bindProvider<DatabaseVerificationStorage> {
        DatabaseVerificationStorageImpl()
    }
    bindProvider<DatabaseVerificationRepository> {
        DatabaseVerificationRepositoryImpl(
            userStorage = instance<DatabaseUserStorage>(),
            verificationStorage = instance<DatabaseVerificationStorage>()
        )
    }
}