import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.settings.SettingsType
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