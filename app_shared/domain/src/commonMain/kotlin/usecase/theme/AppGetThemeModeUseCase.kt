package usecase.theme

import com.nowiwr01p.model.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import core.model.theme.AppTheme

interface AppGetThemeModeUseCase: UseCase<Unit, Flow<AppTheme>>