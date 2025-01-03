package repository.language

import com.nowiwr01p.model.resources.language.appLanguages

class AppLanguageRepositoryImpl: AppLanguageRepository {
    override suspend fun getAppLanguages() = appLanguages
}
