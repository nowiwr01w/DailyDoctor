import android.content.Context
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.settings.SettingsType
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