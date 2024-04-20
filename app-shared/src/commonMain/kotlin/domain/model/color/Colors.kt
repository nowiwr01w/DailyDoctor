import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * BACKGROUND
 */
@get:Composable
val Colors.grayBackgroundColor: Color
    get() = lightGrayBackgroundColor

private val lightGrayBackgroundColor = Color(0xFF3f4257)

@get:Composable
val Colors.secondaryBackgroundColor: Color
    get() = lightSecondaryBackgroundColor

private val lightSecondaryBackgroundColor = Color(0xFF888CAB)

/**
 * TEXT
 */
@get:Composable
val Colors.whiteTextColor: Color
    get() = lightWhiteTextColor

private val lightWhiteTextColor = Color.White

@get:Composable
val Colors.blackTextColor: Color
    get() = lightBlackTextColor

private val lightBlackTextColor = Color.Black

/**
 * COMMON
 */
@get:Composable
val Colors.accentColor: Color
    get() = lightAccentTextColor

private val lightAccentTextColor = Color(0xFFFC4C4C)

@get:Composable
val Colors.indigoColor: Color
    get() = lightIndigoColor

private val lightIndigoColor = Color(0xFF5759CF)

@get:Composable
val Colors.greenColor: Color
    get() = lightGreenColor

private val lightGreenColor = Color(0xFF32B153)

@get:Composable
val Colors.redColor: Color
    get() = lightRedColor

private val lightRedColor = Color(0xFFE34446)

@get:Composable
val Colors.blueColor: Color
    get() = lightBlueColor

private val lightBlueColor = Color(0xFF007AFF)

@get:Composable
val Colors.purpleColor: Color
    get() = lightPurpleColor

private val lightPurpleColor = Color(0xFFA35AD7)