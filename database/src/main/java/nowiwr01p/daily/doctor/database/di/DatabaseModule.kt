package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.repository.user.UserRepositoryDatabase
import nowiwr01p.daily.doctor.database.repository.user.UserRepositoryDatabaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleDatabase = DI.Module("DatabaseModule") {
    /**
     * USER
     */
    bindProvider<UserRepositoryDatabase> {
        UserRepositoryDatabaseImpl()
    }
}