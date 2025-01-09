package nowiwr01p.daily.doctor.local_database.di

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.russhwolf.settings.ExperimentalSettingsApi
import nowiwr01p.daily.doctor.local_database.SettingsType
import nowiwr01p.daily.doctor.local_database.data.SettingsAndroid
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual fun getLocalDatabaseModule() = module {
    SettingsType.entries.forEach { settingsType ->
        factory(named(settingsType.fileName)) {
            val settings = SettingsAndroid(
                context = androidApplication(),
                dispatchers = get<AppDispatchers>()
            )
            settings.createSettings(settingsType)
        }
    }
}