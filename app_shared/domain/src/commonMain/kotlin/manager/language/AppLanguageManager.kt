package manager.language

import com.nowiwr01p.model.model.language.Language
import kotlinx.coroutines.flow.Flow
import model.language.AppLanguageData

interface AppLanguageManager {
    suspend fun getAppLanguagesData(): Flow<AppLanguageData>
    suspend fun selectLanguage(language: Language)
}