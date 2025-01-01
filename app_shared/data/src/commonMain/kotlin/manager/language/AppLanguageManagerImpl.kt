package manager.language

import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import logMessage
import model.language.AppLanguageData
import nowiwr01p.daily.doctor.new_resources.language.Language
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
        logMessage("getAppLanguagesData")
        if (appLanguageData.value.allLanguages.isEmpty()) {
            getAppLanguagesUseCase.execute().also { allLanguages ->
                logMessage("getAppLanguagesData size = ${allLanguages.size}")
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
