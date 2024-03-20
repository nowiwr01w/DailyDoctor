package domain.usecase.app_theme

import domain.repository.AppTheme
import domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

interface GetAppThemeModeUseCase: UseCase<Unit, Flow<AppTheme>>