package components.bottom_sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.bottom_sheet.BottomSheetSize.*

data class BottomSheetParams(
    val topPadding: Dp = 0.dp,
    val showDraggableElement: Boolean = true,
    val bottomSheetSize: BottomSheetSize = WRAP_SIZE,
    val content: @Composable () -> Unit
)

enum class BottomSheetSize {
    MAX_SIZE,
    WRAP_SIZE
}