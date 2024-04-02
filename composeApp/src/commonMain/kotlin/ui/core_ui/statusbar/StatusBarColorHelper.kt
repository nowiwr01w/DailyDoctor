package ui.core_ui.statusbar

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatusBarColorHelper {

    private lateinit var scope: CoroutineScope

    private val _statusBarColor: MutableStateFlow<Color> = MutableStateFlow(Color.Transparent)
    val statusBarColor: StateFlow<Color> = _statusBarColor

    fun init(coroutineScope: CoroutineScope) {
        scope = coroutineScope
    }

    fun setStatusBarColor(color: Color) = scope.launch {
        _statusBarColor.emit(color)
    }
}