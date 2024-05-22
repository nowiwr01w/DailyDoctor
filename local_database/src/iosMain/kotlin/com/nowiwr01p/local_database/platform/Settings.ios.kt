package com.nowiwr01p.local_database.platform

import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Settings {
    actual fun createSettings(type: SettingsType): Settings = NSUserDefaultsSettings
        .Factory()
        .create(type.fileName)
}