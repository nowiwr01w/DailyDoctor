package components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import theme.CustomTheme.colors

@Composable
fun DashedUnderlineText(
    prefix: String,
    highlightedText: String,
    modifier: Modifier
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colors.textColors.blackTextColor.copy(alpha = 0.75f))) {
                append(prefix)
            }
            withStyle(style = SpanStyle(color = colors.textColors.blackTextColor)) {
                append(highlightedText)
            }
        },
        style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Normal),
        color = colors.textColors.blackTextColor,
        onTextLayout = { layoutResult ->
            textLayoutResult = layoutResult
        },
        modifier = modifier.drawBehind {
            textLayoutResult?.let { layoutResult ->
                val startOffset = layoutResult.getHorizontalPosition(
                    offset = prefix.length,
                    usePrimaryDirection = true
                ) - 2.dp.toPx()
                val endOffset = layoutResult.getHorizontalPosition(
                    offset = prefix.length + highlightedText.length,
                    usePrimaryDirection = true
                ) + 6.dp.toPx()

                val segmentWidth = (endOffset - startOffset) / highlightedText.length
                val dashWidth = 0.7f * segmentWidth
                val spaceBetweenDashesWidth = 0.3f * segmentWidth

                val descenderCharacters = "gjpqyфруევკჟუფქყცჭჯ"
                val hasDescenders = highlightedText.any { it in descenderCharacters }
                val yOffset = layoutResult.size.height.toFloat() + when {
                    hasDescenders -> 2
                    else -> 0
                }.dp.toPx()

                drawLine(
                    color = Color.Black,
                    strokeWidth = 1.5.dp.toPx(),
                    start = Offset(x = startOffset, y = yOffset),
                    end = Offset(x = endOffset, y = yOffset),
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(dashWidth, spaceBetweenDashesWidth)
                    )
                )
            }
        }
    )
}