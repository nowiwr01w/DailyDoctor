package nowiwr01p.daily.doctor.local_database.data.repository

import nowiwr01p.daily.doctor.local_database.SettingsKey
import com.nowiwr01p.model.extensions.runCatchingApp
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalSettingsApi::class)
open class BaseSharedPreferencesRepository<Key: SettingsKey, Value>(
    protected val key: Key,
    protected val settings: SuspendSettings
): KoinComponent {
    protected val json by inject<Json>()

    /**
     * GET
     */
    @Suppress("UNCHECKED_CAST")
    protected suspend inline fun <reified T: Value> getValue(defaultValue: T) = with(settings) {
        val keyName = key.keyName
        when (defaultValue) {
            is String -> getString(keyName, defaultValue) as Value
            is Int -> getInt(keyName, defaultValue) as Value
            is Long -> getLong(keyName, defaultValue) as Value
            is Double -> getDouble(keyName, defaultValue) as Value
            is Float -> getFloat(keyName, defaultValue) as Value
            is Boolean -> getBoolean(keyName, defaultValue) as Value
            else -> getCustomValue<T>(defaultValue)
        }
    }

    protected suspend inline fun <reified T: Value> getCustomValue(defaultValue: T) = runCatchingApp {
        settings.getStringOrNull(key.keyName).let { stringValue ->
            if (stringValue != null) {
                json.decodeFromString<T>(stringValue)
            } else {
                defaultValue
            }
        }
    }.getOrElse {
        defaultValue
    }

    /**
     * SAVE
     */
    protected suspend inline fun <reified T: Value> saveValue(value: T) {
        with(settings) {
            val keyName = key.keyName
            when (value) {
                is String -> putString(keyName, value)
                is Int -> putInt(keyName, value)
                is Long -> putLong(keyName, value)
                is Double -> putDouble(keyName, value)
                is Float -> putFloat(keyName, value)
                is Boolean -> putBoolean(keyName, value)
                else -> saveCustomValue<T>(value)
            }
        }
    }

    protected suspend inline fun <reified T: Value> saveCustomValue(value: T) {
        runCatchingApp {
            json.encodeToString(value)
        }.onSuccess { stringValue ->
            settings.putString(key.keyName, stringValue)
        }.onFailure { error ->
            throw error
        }
    }
}