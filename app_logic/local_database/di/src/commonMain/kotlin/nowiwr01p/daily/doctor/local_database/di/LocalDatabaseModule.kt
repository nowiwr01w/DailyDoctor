package nowiwr01p.daily.doctor.local_database.di

import org.koin.dsl.module

val moduleLocalDatabase = module {
    includes(
        getLocalDatabaseModule(),
        moduleLocalDatabaseData
    )
}