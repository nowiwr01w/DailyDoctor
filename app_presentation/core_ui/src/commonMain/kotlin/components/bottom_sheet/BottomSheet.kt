package components.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import components.bottom_sheet.BottomSheetSize.MAX_SIZE
import getScreenHeight
import theme.CustomTheme.colors

@Composable
fun BottomSheet(params: BottomSheetParams) {
    val maxHeight = getScreenHeight() - params.topPadding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = maxHeight)
            .run {
                when (params.bottomSheetSize) {
                    MAX_SIZE -> fillMaxHeight()
                    else -> wrapContentHeight()
                }
            }
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        if (params.showDraggableElement) {
            DraggableTop()
        }
        params.content()
    }
}

@Composable
private fun DraggableTop() = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(top = 6.dp)
        .fillMaxWidth()
        .background(colors.backgroundColors.whiteBackgroundColor)
) {
    Box(
        modifier = Modifier
            .width(36.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.backgroundColors.grayBackgroundColor.copy(alpha = 0.5f))
    )
}