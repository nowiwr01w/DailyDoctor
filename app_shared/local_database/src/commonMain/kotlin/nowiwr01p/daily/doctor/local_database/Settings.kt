package nowiwr01p.daily.doctor.local_database

import com.russhwolf.settings.coroutines.SuspendSettings

@Suppress("OPT_IN_USAGE")
interface Settings {
    fun createSettings(type: SettingsType): SuspendSettings
}