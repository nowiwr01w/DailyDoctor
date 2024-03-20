package data.usecase.theme

import domain.repository.theme.AppThemeRepository
import domain.usecase.theme.GetAppThemeModeUseCase

class GetAppThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): GetAppThemeModeUseCase {

    override suspend fun execute(input: Unit) = repository.getAppThemeMode()
}