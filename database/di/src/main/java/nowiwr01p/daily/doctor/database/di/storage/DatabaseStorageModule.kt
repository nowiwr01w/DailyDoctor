package nowiwr01p.daily.doctor.database.di.storage

import nowiwr01p.daily.doctor.database.data.storage.pin.DatabasePinCodeStorageImpl
import nowiwr01p.daily.doctor.database.data.storage.user.DatabaseUserStorageImpl
import nowiwr01p.daily.doctor.database.data.storage.verification.DatabaseVerificationStorageImpl
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleDatabaseStorage = DI.Module("DatabaseStorageModule") {
    /**
     * USER
     */
    bindProvider<DatabaseUserStorage> {
        DatabaseUserStorageImpl()
    }
    /**
     * VERIFICATION
     */
    bindProvider<DatabaseVerificationStorage> {
        DatabaseVerificationStorageImpl(generator = instance<VerificationCodeGenerator>())
    }
    /**
     * PIN
     */
    bindProvider<DatabasePinCodeStorage> {
        DatabasePinCodeStorageImpl()
    }
}