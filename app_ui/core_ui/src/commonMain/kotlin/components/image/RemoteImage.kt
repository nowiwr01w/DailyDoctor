package components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RemoteImage(
    url: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    scale: ContentScale = ContentScale.Fit,
    loadingPlaceholder: DrawableResource? = null,
    errorPlaceholder: DrawableResource? = null
) {
    AsyncImage(
        modifier = modifier,
        contentScale = scale,
        colorFilter = if (color != null) ColorFilter.tint(color) else null,
        contentDescription = "AppImage",
        error = if (errorPlaceholder != null) painterResource(errorPlaceholder) else null,
        placeholder = if (loadingPlaceholder != null) painterResource(loadingPlaceholder) else null,
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(url)
            .crossfade(true)
            .build()
    )
}