package nowiwr01p.daily.doctor.local_database.data

import nowiwr01p.daily.doctor.local_database.Settings
import nowiwr01p.daily.doctor.local_database.SettingsType
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.toSuspendSettings

@OptIn(ExperimentalSettingsApi::class)
class SettingsIos(
    private val dispatchers: AppDispatchers
): Settings {

    override fun createSettings(type: SettingsType) = NSUserDefaultsSettings
        .Factory()
        .create(type.fileName)
        .toSuspendSettings(dispatchers.io)
}