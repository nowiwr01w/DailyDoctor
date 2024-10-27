import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.settings.SettingsType
import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual fun getLocalDatabaseModule() = module {
    SettingsType.entries.forEach { settingsType ->
        factory(named(settingsType.fileName)) {
            val settings = SettingsDesktop(dispatchers = get<AppDispatchers>())
            settings.createSettings(settingsType)
        }
    }
}