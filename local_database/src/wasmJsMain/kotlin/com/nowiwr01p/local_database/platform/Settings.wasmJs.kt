package com.nowiwr01p.local_database.platform

import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings
import kotlinx.browser.localStorage

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Settings {
    actual fun createSettings(type: SettingsType): Settings = StorageSettings(
        delegate = localStorage // TODO: Check how to use multiple nodes
    )
}