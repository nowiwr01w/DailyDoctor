package nowiwr01p.daily.doctor.local_database.data

import nowiwr01p.daily.doctor.local_database.Settings
import nowiwr01p.daily.doctor.local_database.SettingsType
import android.content.Context
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.toSuspendSettings

@OptIn(ExperimentalSettingsApi::class)
class SettingsAndroid(
    private val context: Context,
    private val dispatchers: AppDispatchers
): Settings {

    override fun createSettings(type: SettingsType) = SharedPreferencesSettings(
        delegate = context.getSharedPreferences(type.fileName, Context.MODE_PRIVATE)
    ).toSuspendSettings(dispatchers.io)
}