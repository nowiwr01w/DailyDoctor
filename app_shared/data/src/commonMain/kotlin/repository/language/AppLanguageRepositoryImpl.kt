package repository.language

import nowiwr01p.daily.doctor.new_resources.language.appLanguages

class AppLanguageRepositoryImpl: AppLanguageRepository {
    override suspend fun getAppLanguages() = appLanguages
}
