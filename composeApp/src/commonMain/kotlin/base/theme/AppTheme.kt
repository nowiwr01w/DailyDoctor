package base.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import base.theme.shape.AppShapes
import base.theme.typography.getAppTypography
import currentPlatform
import org.kodein.di.compose.rememberInstance

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val shapes by rememberInstance<AppShapes>(currentPlatform)
    MaterialTheme(
        typography = getAppTypography(),
        shapes = shapes.shapes,
        content = content
    )
}