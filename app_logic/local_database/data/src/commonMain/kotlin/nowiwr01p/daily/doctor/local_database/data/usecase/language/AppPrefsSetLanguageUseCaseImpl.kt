package nowiwr01p.daily.doctor.local_database.data.usecase.language

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.local_database.domain.repository.language.AppLanguageRepositoryPrefs
import nowiwr01p.daily.doctor.local_database.domain.usecase.language.AppPrefsSetLanguageUseCase

class AppPrefsSetLanguageUseCaseImpl(
    private val repository: AppLanguageRepositoryPrefs
): AppPrefsSetLanguageUseCase {
    /**
     * SAVE LANGUAGE IN PREFS
     */
    override suspend fun execute(input: Language) = repository.setAppLanguage(input)
}