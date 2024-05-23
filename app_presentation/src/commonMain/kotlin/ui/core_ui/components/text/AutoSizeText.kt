package ui.core_ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.times
import nowiwr01p.daily.doctor.app_presentation.theme.CustomTheme.colors

@Composable
fun AutoSizeText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = colors.textColors.blackTextColor,
    maxLines: Int = Int.MAX_VALUE
) {
    var showText by remember { mutableStateOf(false) }
    var currentTextStyle by remember { mutableStateOf(style) }
    Text(
        text,
        style = currentTextStyle,
        color = color,
        maxLines = maxLines,
        softWrap = false,
        onTextLayout = { layoutResult ->
            when {
                layoutResult.didOverflowWidth -> {
                    currentTextStyle = currentTextStyle.copy(
                        fontSize = 0.95 * currentTextStyle.fontSize
                    )
                }
                else -> showText = true
            }
        },
        modifier = modifier.drawWithContent {
            if (showText) drawContent()
        }
    )
}