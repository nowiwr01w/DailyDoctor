package nowiwr01p.daily.doctor.app_presentation.theme.shape.platform

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import nowiwr01p.daily.doctor.app_presentation.theme.shape.AppShapes

class WebShapes: AppShapes {
    override val shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(32.dp)
    )
}