package domain.model.color.data.text.classic

import androidx.compose.ui.graphics.Color
import domain.model.color.data.text.AppTextColors

data class ClassicLightTextColors(
    override val blackTextColor: Color = Color.Black,
    override val grayTextColor: Color = Color.White,
    override val blueTextColor: Color = Color(0xFF5683ea),
    override val whiteTextColor: Color = Color.White,
    override val redTextColor: Color = Color(0xFFe15e41)
): AppTextColors