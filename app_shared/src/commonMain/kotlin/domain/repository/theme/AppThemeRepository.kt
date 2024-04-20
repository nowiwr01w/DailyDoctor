package domain.repository.theme

import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    suspend fun getAppThemeMode(): Flow<AppTheme>
    suspend fun setAppThemeMode(theme: AppTheme)
}