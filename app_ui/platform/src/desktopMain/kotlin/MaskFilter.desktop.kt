import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadius)
}