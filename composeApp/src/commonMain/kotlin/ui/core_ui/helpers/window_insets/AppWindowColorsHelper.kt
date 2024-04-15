package ui.core_ui.helpers.window_insets

import androidx.compose.ui.graphics.Color
import domain.coroutines.app_scope.AppScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppWindowColorsHelper(
    private val appScope: AppScope
) {
    private val _statusBarColor = MutableStateFlow<WindowColorsData?>(null)
    val appWindowColors = _statusBarColor.asStateFlow()

    fun setAppWindowColors(windowColorsData: WindowColorsData) = appScope.scope.launch {
        _statusBarColor.emit(windowColorsData)
    }
}

data class WindowColorsData(
    val statusBarColor: Color,
    val navigationBarColor: Color
)