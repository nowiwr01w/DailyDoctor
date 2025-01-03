package manager.language

import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.language.AppLanguageData
import com.nowiwr01p.model.resources.language.Language
import usecase.language.GetAppLanguagesUseCase

class AppLanguageManagerImpl(
    private val getAppLanguagesUseCase: GetAppLanguagesUseCase
): AppLanguageManager {
    /**
     * DATA
     */
    private val appLanguageData = MutableStateFlow(AppLanguageData())

    /**
     * GET APP LANGUAGES DATA
     */
    override suspend fun getAppLanguagesData(): Flow<AppLanguageData> {
        if (appLanguageData.value.allLanguages.isEmpty()) {
            getAppLanguagesUseCase.execute().also { allLanguages ->
                val updatedAppLanguageData = appLanguageData.value.copy(allLanguages = allLanguages)
                appLanguageData.emit(updatedAppLanguageData)
            }
        }
        return appLanguageData.asStateFlow()
    }

    /**
     * SELECT LANGUAGE
     */
    override suspend fun selectLanguage(language: Language) {
        val updatedAppLanguageData = appLanguageData.value.copy(selectedLanguage = language)
        appLanguageData.emit(updatedAppLanguageData)
    }
}
