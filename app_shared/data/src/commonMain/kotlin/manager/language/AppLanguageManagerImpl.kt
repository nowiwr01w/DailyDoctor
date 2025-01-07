package manager.language

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.language.AppLanguageData
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsGetLanguageUseCase
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsSetLanguageUseCase
import usecase.language.GetAppLanguagesUseCase

class AppLanguageManagerImpl(
    private val getAppLanguagesUseCase: GetAppLanguagesUseCase,
    private val appPrefsGetLanguageUseCase: AppPrefsGetLanguageUseCase,
    private val appPrefsSetLanguageUseCase: AppPrefsSetLanguageUseCase
): AppLanguageManager {
    /**
     * DATA
     */
    private val appLanguageData = MutableStateFlow(AppLanguageData())

    /**
     * GET APP LANGUAGES DATA
     */
    override suspend fun getAppLanguagesData(): Flow<AppLanguageData> {
        getAppLanguagesUseCase.execute().also { allLanguages ->
            val updatedAppLanguageData = appLanguageData.value.copy(
                allLanguages = allLanguages,
                selectedLanguage = appPrefsGetLanguageUseCase.execute()
            )
            appLanguageData.emit(updatedAppLanguageData)
        }
        return appLanguageData.asStateFlow()
    }

    /**
     * SELECT LANGUAGE
     */
    override suspend fun selectLanguage(language: Language) {
        appPrefsSetLanguageUseCase.execute(language)
        val updatedAppLanguageData = appLanguageData.value.copy(selectedLanguage = language)
        appLanguageData.emit(updatedAppLanguageData)
    }
}
