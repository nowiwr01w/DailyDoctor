package components.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SinusoidalDivider(
    modifier: Modifier,
    dividerHeight: Dp = 24.dp,
    color: Color = Color.Gray,
    strokeWidth: Float = 2f
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(dividerHeight)
    ) {
        val width = size.width
        val height = dividerHeight.toPx()
        val amplitude = height / 2
        val frequency = 2 * amplitude * PI / width

        repeat(width.toInt()) { x ->
            drawLine(
                color = color,
                start = Offset(
                    x = x.toFloat(),
                    y = height / 2 + amplitude * sin(frequency * x).toFloat()
                ),
                end = Offset(
                    x = x + 1f,
                    y = height / 2 + amplitude * sin(frequency * (x + 1)).toFloat()
                ),
                strokeWidth = strokeWidth
            )
        }
    }
}