package ui.core_ui.helpers.snack_bar

import domain.coroutines.app_scope.AppScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ui.core_ui.helpers.snack_bar.data.SnackBarParams

class SnackBarHelper(
    private val appScope: AppScope
) {
    private val _params = MutableStateFlow<SnackBarParams?>(null)
    val params = _params.asStateFlow()

    fun showSnackBar(params: SnackBarParams) = appScope.scope.launch {
        _params.emit(params)
        delay(params.duration.timeMillis)
        _params.emit(null)
    }
}