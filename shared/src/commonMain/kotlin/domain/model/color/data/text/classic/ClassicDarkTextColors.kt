package domain.model.color.data.text.classic

import androidx.compose.ui.graphics.Color
import domain.model.color.data.text.AppTextColors

data class ClassicDarkTextColors(
    override val blackTextColor: Color = Color.White,
    override val grayTextColor: Color = Color.White,
    override val blueTextColor: Color = Color.White,
    override val whiteTextColor: Color = Color.White
): AppTextColors