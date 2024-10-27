import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import kotlinx.browser.localStorage

@OptIn(ExperimentalSettingsApi::class)
class SettingsWeb(
    private val dispatchers: AppDispatchers
): Settings {

    override fun createSettings(type: SettingsType): SuspendSettings = StorageSettings(
        delegate = localStorage // TODO: Check how to use multiple nodes
    ).toSuspendSettings(dispatchers.io)
}