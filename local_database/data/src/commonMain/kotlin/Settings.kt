import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Settings {
    fun createSettings(type: SettingsType): Settings
}