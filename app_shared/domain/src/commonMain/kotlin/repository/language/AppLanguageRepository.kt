package repository.language

import nowiwr01p.daily.doctor.new_resources.language.Language

interface AppLanguageRepository {
    suspend fun getAppLanguages(): List<Language>
}
