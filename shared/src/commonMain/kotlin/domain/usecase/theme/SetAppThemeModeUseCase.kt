package domain.usecase.theme

import domain.repository.theme.AppTheme
import com.nowiwr01p.model.usecase.UseCase

interface SetAppThemeModeUseCase: UseCase<AppTheme, Unit>