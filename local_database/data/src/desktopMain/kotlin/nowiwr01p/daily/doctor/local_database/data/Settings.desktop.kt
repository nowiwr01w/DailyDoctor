package nowiwr01p.daily.doctor.local_database.data

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import nowiwr01p.daily.doctor.local_database.Settings
import nowiwr01p.daily.doctor.local_database.SettingsType
import java.util.prefs.Preferences

class SettingsDesktop(
    private val dispatchers: AppDispatchers
): Settings {

    @OptIn(ExperimentalSettingsApi::class)
    override fun createSettings(type: SettingsType): SuspendSettings = PreferencesSettings(
        delegate = Preferences.userRoot().node(type.fileName)
    ).toSuspendSettings(dispatchers.io)
}