import com.nowiwr01p.model.settings.SettingsType
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun getLocalDatabaseModule() = module {
    SettingsType.entries.forEach { settingsType ->
        factory(named(settingsType)) {
            Settings().createSettings(settingsType)
        }
    }
}