package components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

data class LocalWindowInsetsData(
    private val appTopPadding: Dp,
    private val appBottomPadding: Dp
) {
    var topPadding by mutableStateOf(appTopPadding)
        private set

    var bottomPadding by mutableStateOf(appBottomPadding)
        private set

    fun updateInsets(other: LocalWindowInsetsData) {
        topPadding = other.appTopPadding
        bottomPadding = other.appBottomPadding
    }
}

@Composable
internal fun getWindowInsets(): State<LocalWindowInsetsData> {
    val data = with(LocalDensity.current) {
        LocalWindowInsetsData(
            appTopPadding = WindowInsets.statusBars.getTop(this).toDp(),
            appBottomPadding = WindowInsets.navigationBars.getBottom(this).toDp()
        )
    }
    return derivedStateOf { data }
}
