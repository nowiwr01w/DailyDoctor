package usecase.theme

import repository.theme.AppThemeRepository

class AppGetThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): AppGetThemeModeUseCase {

    override suspend fun execute(input: Unit) = repository.getAppThemeMode()
}