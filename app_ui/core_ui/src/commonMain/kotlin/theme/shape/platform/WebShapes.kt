package theme.shape.platform

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import theme.shape.AppShapes

@Immutable
class WebShapes: AppShapes {
    override val extraSmall: CornerBasedShape = RoundedCornerShape(4.dp)
    override val small: CornerBasedShape = RoundedCornerShape(8.dp)
    override val medium: CornerBasedShape = RoundedCornerShape(16.dp)
    override val large: CornerBasedShape = RoundedCornerShape(24.dp)
    override val extraLarge: CornerBasedShape = RoundedCornerShape(32.dp)
    override val circle: CornerBasedShape = RoundedCornerShape(50.dp)
}
