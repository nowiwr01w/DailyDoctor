package data.usecase.theme

import domain.repository.theme.AppTheme
import domain.repository.theme.AppThemeRepository
import domain.usecase.theme.SetAppThemeModeUseCase

class SetAppThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): SetAppThemeModeUseCase {

    override suspend fun execute(input: AppTheme) {
            repository.setAppThemeMode(input)
    }
}