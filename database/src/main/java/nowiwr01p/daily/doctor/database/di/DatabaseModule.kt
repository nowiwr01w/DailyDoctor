package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.repository.auth.DatabaseAuthRepositoryImpl
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.database.repository.verification.DatabaseVerificationRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleDatabase = DI.Module("DatabaseModule") {
    /**
     * AUTH
     */
    bindProvider<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl()
    }
    /**
     * VERIFICATION
     */
    bindProvider<DatabaseVerificationRepository> {
        DatabaseVerificationRepositoryImpl()
    }
}