package nowiwr01p.daily.doctor.database.di.storage

import nowiwr01p.daily.doctor.database.data.storage.brand_config.DatabaseBrandConfigStorageImpl
import nowiwr01p.daily.doctor.database.data.storage.pin.DatabasePinCodeStorageImpl
import nowiwr01p.daily.doctor.database.data.storage.user.DatabaseUserStorageImpl
import nowiwr01p.daily.doctor.database.data.storage.verification.DatabaseVerificationStorageImpl
import nowiwr01p.daily.doctor.database.domain.generator.VerificationCodeGenerator
import nowiwr01p.daily.doctor.database.domain.storage.brand_config.DatabaseBrandConfigStorage
import nowiwr01p.daily.doctor.database.domain.storage.pin.DatabasePinCodeStorage
import nowiwr01p.daily.doctor.database.domain.storage.user.DatabaseUserStorage
import nowiwr01p.daily.doctor.database.domain.storage.verification.DatabaseVerificationStorage
import org.koin.dsl.module

internal val moduleDatabaseStorage = module {
    /**
     * BRAND CONFIG
     */
    factory<DatabaseBrandConfigStorage> {
        DatabaseBrandConfigStorageImpl()
    }
    /**
     * USER
     */
    factory<DatabaseUserStorage> {
        DatabaseUserStorageImpl()
    }
    /**
     * VERIFICATION
     */
    factory<DatabaseVerificationStorage> {
        DatabaseVerificationStorageImpl()
    }
    /**
     * PIN
     */
    factory<DatabasePinCodeStorage> {
        DatabasePinCodeStorageImpl()
    }
}