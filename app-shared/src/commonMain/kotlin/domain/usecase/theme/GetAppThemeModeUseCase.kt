package domain.usecase.theme

import domain.repository.theme.AppTheme
import com.nowiwr01p.model.usecase.UseCase
import kotlinx.coroutines.flow.Flow

interface GetAppThemeModeUseCase: UseCase<Unit, Flow<AppTheme>>