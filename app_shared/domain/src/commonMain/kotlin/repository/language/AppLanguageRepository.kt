package repository.language

import com.nowiwr01p.model.model.language.Language

interface AppLanguageRepository {
    suspend fun getAppLanguages(): List<Language>
}