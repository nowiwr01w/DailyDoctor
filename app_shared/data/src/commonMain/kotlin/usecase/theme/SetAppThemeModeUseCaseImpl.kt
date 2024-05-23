package usecase.theme

import core.model.theme.AppTheme
import repository.theme.AppThemeRepository

class SetAppThemeModeUseCaseImpl(
    private val repository: AppThemeRepository
): SetAppThemeModeUseCase {

    override suspend fun execute(input: AppTheme) {
        repository.setAppThemeMode(input)
    }
}