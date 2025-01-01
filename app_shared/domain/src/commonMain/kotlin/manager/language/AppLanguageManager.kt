package manager.language

import kotlinx.coroutines.flow.Flow
import model.language.AppLanguageData
import nowiwr01p.daily.doctor.new_resources.language.Language

interface AppLanguageManager {
    suspend fun getAppLanguagesData(): Flow<AppLanguageData>
    suspend fun selectLanguage(language: Language)
}
