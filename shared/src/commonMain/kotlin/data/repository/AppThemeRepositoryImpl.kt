package data.repository

import domain.repository.AppTheme
import domain.repository.AppThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AppThemeRepositoryImpl: AppThemeRepository {

    private val themeMode = MutableStateFlow(AppTheme.LIGHT) // TODO: Change to local storage

    override suspend fun getAppThemeMode() = themeMode

    override suspend fun setAppThemeMode(theme: AppTheme) = themeMode.update { theme }
}