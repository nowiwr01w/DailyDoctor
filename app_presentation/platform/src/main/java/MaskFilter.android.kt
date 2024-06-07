import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.NativePaint

actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}