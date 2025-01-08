package extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import setMaskFilter
import theme.CustomTheme

@Composable
fun Modifier.advancedShadow(
    shadowColor: Color = CustomTheme.colors.textColors.blackTextColor.copy(alpha = 0.1f),
    width: Float = 1f,
    cornerRadius: Dp = 16.dp,
    outsideBlurRadius: Dp = 8.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
): Modifier {
    return drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint().apply {
                color = shadowColor.toArgb()
            }
            val spreadPixel = width.dp.toPx()
            val leftPixel = -spreadPixel + offsetX.toPx()
            val topPixel = -spreadPixel + offsetY.toPx()
            val rightPixel = spreadPixel + size.width
            val bottomPixel = spreadPixel + size.height

            if (outsideBlurRadius != 0.dp) {
                frameworkPaint.setMaskFilter(outsideBlurRadius.toPx())
            }
            canvas.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = cornerRadius.toPx(),
                radiusY = cornerRadius.toPx(),
                paint
            )
        }
    }
}