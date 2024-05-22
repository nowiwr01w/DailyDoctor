package com.nowiwr01p.local_database.platform

import com.nowiwr01p.model.settings.SettingsType
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun createSettingsModule() = module {
    SettingsType.entries.forEach { settingsType ->
        factory(named(settingsType)) {
            Settings().createSettings(settingsType)
        }
    }
}