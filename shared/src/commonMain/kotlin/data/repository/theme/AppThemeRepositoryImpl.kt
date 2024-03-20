package data.repository.theme

import domain.repository.theme.AppTheme
import domain.repository.theme.AppThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AppThemeRepositoryImpl: AppThemeRepository {

    private val themeMode = MutableStateFlow(AppTheme.LIGHT) // TODO: Change to local storage

    override suspend fun getAppThemeMode() = themeMode

    override suspend fun setAppThemeMode(theme: AppTheme) = themeMode.update { theme }
}