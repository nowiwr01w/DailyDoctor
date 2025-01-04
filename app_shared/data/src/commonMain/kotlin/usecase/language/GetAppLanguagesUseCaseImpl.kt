package usecase.language

import com.nowiwr01p.model.resources.language.appLanguages

class GetAppLanguagesUseCaseImpl: GetAppLanguagesUseCase {
    override suspend fun execute(input: Unit) = appLanguages
}
