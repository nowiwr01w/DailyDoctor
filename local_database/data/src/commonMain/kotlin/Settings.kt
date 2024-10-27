import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.coroutines.SuspendSettings

@Suppress("OPT_IN_USAGE")
interface Settings {
    fun createSettings(type: SettingsType): SuspendSettings
}