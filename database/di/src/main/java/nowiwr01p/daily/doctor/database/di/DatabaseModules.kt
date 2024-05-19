package nowiwr01p.daily.doctor.database.di

import nowiwr01p.daily.doctor.database.di.repository.moduleDatabaseRepository
import nowiwr01p.daily.doctor.database.di.storage.moduleDatabaseStorage

val databaseModules = listOf(
    moduleDatabaseStorage,
    moduleDatabaseRepository
)