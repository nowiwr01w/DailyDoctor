package components.divider

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.LocalWindowInsets

@Composable
fun NavigationBarSpacer() {
    val insets = LocalWindowInsets.current
    Spacer(
        modifier = Modifier.height(insets.bottomPadding)
    )
}
