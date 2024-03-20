package domain.repository

import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    suspend fun getAppThemeMode(): Flow<AppTheme>
    suspend fun setAppThemeMode(theme: AppTheme)
}