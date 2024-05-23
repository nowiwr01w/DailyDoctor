package helpers.snack_bar

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import helpers.snack_bar.data.SnackBarParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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