package domain.usecase.theme

import domain.repository.theme.AppTheme
import domain.usecase.UseCase

interface SetAppThemeModeUseCase: UseCase<AppTheme, Unit>