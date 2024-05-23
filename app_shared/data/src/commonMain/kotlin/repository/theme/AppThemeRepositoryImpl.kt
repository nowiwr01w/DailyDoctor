package repository.theme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import core.model.theme.AppTheme

class AppThemeRepositoryImpl: AppThemeRepository {

    private val themeMode = MutableStateFlow(AppTheme.LIGHT) // TODO: Change to local storage

    override suspend fun getAppThemeMode() = themeMode

    override suspend fun setAppThemeMode(theme: AppTheme) = themeMode.update { theme }
}