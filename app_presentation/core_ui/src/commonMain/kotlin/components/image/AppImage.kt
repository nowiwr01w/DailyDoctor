package components.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppImage(
    image: DrawableResource,
    modifier: Modifier = Modifier,
    color: Color? = null,
    scale: ContentScale = ContentScale.Fit,
) {
    Image(
        painter = painterResource(image),
        contentDescription = "AppImage",
        colorFilter = if (color != null) ColorFilter.tint(color) else null,
        contentScale = scale,
        modifier = modifier
    )
}