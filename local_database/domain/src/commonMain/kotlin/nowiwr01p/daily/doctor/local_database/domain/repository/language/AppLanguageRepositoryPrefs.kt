package nowiwr01p.daily.doctor.local_database.domain.repository.language

import com.nowiwr01p.model.resources.language.Language

interface AppLanguageRepositoryPrefs {
    suspend fun getAppLanguage(): String
    suspend fun setAppLanguage(language: Language)
}