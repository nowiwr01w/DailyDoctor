package ui.core_ui.helpers.window_insets.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
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

val LocalWindowInsets = staticCompositionLocalOf<LocalWindowInsetsData> {
    error("No LocalWindowInsetsData set")
}

@Composable
fun ProviderLocalWindowInsets(
    insetsData: LocalWindowInsetsData,
    content: @Composable () -> Unit
) {
    val insets = remember { insetsData }
    insets.updateInsets(insetsData)
    CompositionLocalProvider(LocalWindowInsets provides insets) {
        content()
    }
}