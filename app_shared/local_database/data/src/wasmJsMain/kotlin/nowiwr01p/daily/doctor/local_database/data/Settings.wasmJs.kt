package nowiwr01p.daily.doctor.local_database.data

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.browser.localStorage
import nowiwr01p.daily.doctor.local_database.Settings
import nowiwr01p.daily.doctor.local_database.SettingsType

@OptIn(ExperimentalSettingsApi::class)
class SettingsWeb(
    private val dispatchers: AppDispatchers
): Settings {

    override fun createSettings(type: SettingsType): SuspendSettings = StorageSettings(
        delegate = localStorage // TODO: Check how to use multiple nodes
    ).toSuspendSettings(dispatchers.io)
}