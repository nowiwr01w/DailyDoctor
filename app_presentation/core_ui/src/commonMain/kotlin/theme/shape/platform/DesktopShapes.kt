package theme.shape.platform

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import theme.shape.AppShapes

class DesktopShapes: AppShapes {
    override val shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(32.dp)
    )
}