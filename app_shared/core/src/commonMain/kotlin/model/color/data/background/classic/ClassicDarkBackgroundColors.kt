package core.model.color.data.background.classic

import androidx.compose.ui.graphics.Color
import core.model.color.data.background.AppBackgroundColors

data class ClassicDarkBackgroundColors(
    override val whiteBackgroundColor: Color = Color.White,
    override val grayBackgroundColor: Color = Color(0xFF3f4257),
    override val blueBackgroundColor: Color = Color(0xFFeef1fa),
    override val redBackgroundColor: Color = Color(0xFFe15e41)
): AppBackgroundColors