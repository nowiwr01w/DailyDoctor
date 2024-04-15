package ui.core_ui.helpers.snack_bar.data

import androidx.compose.ui.unit.Dp

sealed class SnackBarDirection(open val margin: Dp) {
    data class SnackBarDirectionTop(override val margin: Dp): SnackBarDirection(margin)
    data class SnackBarDirectionBottom(override val margin: Dp): SnackBarDirection(margin)
}