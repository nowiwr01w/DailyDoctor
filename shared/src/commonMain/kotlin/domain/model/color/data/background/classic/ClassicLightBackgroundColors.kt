package domain.model.color.data.background.classic

import androidx.compose.ui.graphics.Color
import domain.model.color.data.background.AppBackgroundColors

data class ClassicLightBackgroundColors(
    override val whiteBackgroundColor: Color = Color.White,
    override val grayBackgroundColor: Color = Color.White,
    override val blueBackgroundColor: Color = Color.White,
    override val redBackgroundColor: Color = Color.White
): AppBackgroundColors