package com.nowiwr01p.local_database.platform

import com.nowiwr01p.model.settings.SettingsType
import org.kodein.di.DI
import org.kodein.di.bindProvider

actual fun createSettingsModule() = DI.Module("SettingsModuleWasmIos") {
    SettingsType.entries.forEach { settingsType ->
        bindProvider(settingsType) {
            Settings().createSettings(settingsType)
        }
    }
}