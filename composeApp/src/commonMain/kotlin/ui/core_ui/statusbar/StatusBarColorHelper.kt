package ui.core_ui.statusbar

import androidx.compose.ui.graphics.Color
import domain.coroutines.app_scope.AppScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatusBarColorHelper(
    private val appScope: AppScope
) {

    private val _statusBarColor = MutableStateFlow(Color.Transparent)
    val statusBarColor = _statusBarColor.asStateFlow()

    fun setStatusBarColor(color: Color) = appScope.scope.launch {
        _statusBarColor.emit(color)
    }
}