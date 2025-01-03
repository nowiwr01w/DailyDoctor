package manager.language

import kotlinx.coroutines.flow.Flow
import model.language.AppLanguageData
import com.nowiwr01p.model.resources.language.Language

interface AppLanguageManager {
    suspend fun getAppLanguagesData(): Flow<AppLanguageData>
    suspend fun selectLanguage(language: Language)
}
