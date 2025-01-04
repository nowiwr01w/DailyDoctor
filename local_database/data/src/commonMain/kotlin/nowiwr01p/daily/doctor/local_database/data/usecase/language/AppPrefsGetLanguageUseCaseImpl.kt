package nowiwr01p.daily.doctor.local_database.data.usecase.language

import com.nowiwr01p.model.resources.language.appLanguages
import com.nowiwr01p.model.resources.language.defaultSelectedLanguage
import nowiwr01p.daily.doctor.local_database.domain.repository.language.AppLanguageRepositoryPrefs
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsGetLanguageUseCase

class AppPrefsGetLanguageUseCaseImpl(
    private val repository: AppLanguageRepositoryPrefs
): AppPrefsGetLanguageUseCase {
    /**
     * GET LANGUAGE FROM PREFS
     */
    override suspend fun execute(input: Unit) = repository.getAppLanguage().let { languageCode ->
        appLanguages.find { it.code == languageCode } ?: defaultSelectedLanguage
    }
}