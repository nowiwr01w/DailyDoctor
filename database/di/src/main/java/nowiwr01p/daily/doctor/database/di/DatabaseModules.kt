package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.di.generator.moduleDatabaseGenerator
import nowiwr01p.daily.doctor.database.di.repository.moduleDatabaseRepository
import nowiwr01p.daily.doctor.database.di.storage.moduleDatabaseStorage
import nowiwr01p.daily.doctor.database.init_values.di.moduleDatabaseInitValues

val databaseModules = listOf(
    moduleDatabaseInitValues,
    moduleDatabaseStorage,
    moduleDatabaseRepository,
    moduleDatabaseGenerator
)