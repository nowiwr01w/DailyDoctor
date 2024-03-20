package data.usecase

import domain.repository.AppThemeRepository
import domain.usecase.app_theme.GetAppThemeModeUseCase

class GetAppThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): GetAppThemeModeUseCase {

    override suspend fun execute(input: Unit) = repository.getAppThemeMode()
}