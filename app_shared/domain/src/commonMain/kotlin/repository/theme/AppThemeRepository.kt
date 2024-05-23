package repository.theme

import kotlinx.coroutines.flow.Flow
import core.model.theme.AppTheme

interface AppThemeRepository {
    suspend fun getAppThemeMode(): Flow<AppTheme>
    suspend fun setAppThemeMode(theme: AppTheme)
}