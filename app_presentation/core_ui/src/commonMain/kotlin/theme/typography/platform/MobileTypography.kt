package theme.typography.platform

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import theme.typography.AppTypography

class MobileTypography: AppTypography {
    override fun getTypography(fontFamily: FontFamily) = Typography(
        h1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 36.sp,
            lineHeight = 40.sp
        ),
        h2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 27.sp,
            lineHeight = 32.sp
        ),
        h3 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        h4 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 32.sp
        ),
        h5 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        h6 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        body1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        body2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 24.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 14.sp
        ),
        button = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        )
    )
}