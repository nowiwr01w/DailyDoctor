package com.nowiwr01p.local_database.platform

import android.content.Context
import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Settings(
    private val context: Context
) {
    actual fun createSettings(type: SettingsType): Settings = SharedPreferencesSettings(
        delegate = context.getSharedPreferences(type.fileName, Context.MODE_PRIVATE)
    )
}