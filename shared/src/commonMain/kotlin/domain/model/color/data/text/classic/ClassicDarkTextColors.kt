package domain.model.color.data.text.classic

import androidx.compose.ui.graphics.Color
import domain.model.color.data.text.AppTextColors

data class ClassicDarkTextColors(
    override val blackTextColor: Color = Color.White,
    override val grayTextColor: Color = Color(0xFF5b5c61),
    override val blueTextColor: Color = Color(0xFF3960c9),
    override val whiteTextColor: Color = Color.White,
    override val redTextColor: Color = Color(0xFFe15e41)
): AppTextColors