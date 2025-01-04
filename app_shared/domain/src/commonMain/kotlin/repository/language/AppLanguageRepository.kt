package repository.language

import com.nowiwr01p.model.resources.language.Language

interface AppLanguageRepository {
    suspend fun getAppLanguages(): List<Language>
}
