package usecase.language

import repository.language.AppLanguageRepository

class GetAppLanguagesUseCaseImpl(
    private val repository: AppLanguageRepository
): GetAppLanguagesUseCase {
    override suspend fun execute(input: Unit) = repository.getAppLanguages()
}
