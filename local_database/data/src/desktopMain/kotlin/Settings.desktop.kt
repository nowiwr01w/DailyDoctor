import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Settings {
    actual fun createSettings(type: SettingsType): Settings = PreferencesSettings(
        delegate = Preferences.userRoot().node(type.fileName)
    )
}