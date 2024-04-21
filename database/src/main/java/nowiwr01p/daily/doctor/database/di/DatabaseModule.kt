package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.repository.user.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.repository.user.DatabaseAuthRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleDatabase = DI.Module("DatabaseModule") {
    /**
     * USER
     */
    bindProvider<DatabaseAuthRepository> {
        DatabaseAuthRepositoryImpl()
    }
}