package com.nowiwr01p.local_database.platform

import com.nowiwr01p.model.settings.SettingsType
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun getLocalDatabaseModule() = module {
    SettingsType.entries.forEach { settingsType ->
        factory(named(settingsType)) {
            Settings(androidApplication()).createSettings(settingsType)
        }
    }
}