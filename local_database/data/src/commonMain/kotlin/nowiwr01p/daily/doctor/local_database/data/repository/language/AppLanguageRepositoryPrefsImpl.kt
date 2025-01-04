package nowiwr01p.daily.doctor.local_database.data.repository.language

import nowiwr01p.daily.doctor.local_database.SettingsKey.LanguageKey
import com.nowiwr01p.model.resources.language.Language
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import nowiwr01p.daily.doctor.local_database.domain.repository.language.AppLanguageRepositoryPrefs
import nowiwr01p.daily.doctor.local_database.data.repository.BaseSharedPreferencesRepository

@OptIn(ExperimentalSettingsApi::class)
class AppLanguageRepositoryPrefsImpl(
    settings: SuspendSettings
): AppLanguageRepositoryPrefs, BaseSharedPreferencesRepository<LanguageKey, String>(
    key = LanguageKey,
    settings = settings
) {
    /**
     * GET
     */
    override suspend fun getAppLanguage() = getValue(defaultValue = Language.English.code)

    /**
     * SET
     */
    override suspend fun setAppLanguage(language: Language) = saveValue(language.code)
}