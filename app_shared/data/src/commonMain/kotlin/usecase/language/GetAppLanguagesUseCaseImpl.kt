package usecase.language

import com.nowiwr01p.model.model.language.Language
import repository.language.AppLanguageRepository

class GetAppLanguagesUseCaseImpl(
    private val repository: AppLanguageRepository
): GetAppLanguagesUseCase {
    override suspend fun execute(input: Unit) = repository.getAppLanguages()
}