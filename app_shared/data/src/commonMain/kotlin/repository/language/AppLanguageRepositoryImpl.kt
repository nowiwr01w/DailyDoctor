package repository.language

import com.nowiwr01p.model.model.language.appLanguages

class AppLanguageRepositoryImpl: AppLanguageRepository {
    override suspend fun getAppLanguages() = appLanguages
}