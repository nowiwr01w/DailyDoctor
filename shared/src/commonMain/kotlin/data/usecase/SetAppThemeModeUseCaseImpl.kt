package data.usecase

import domain.repository.AppTheme
import domain.repository.AppThemeRepository
import domain.usecase.app_theme.SetAppThemeModeUseCase

class SetAppThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): SetAppThemeModeUseCase {

    override suspend fun execute(input: AppTheme) {
            repository.setAppThemeMode(input)
    }
}