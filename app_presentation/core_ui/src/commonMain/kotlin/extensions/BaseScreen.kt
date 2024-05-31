package extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.LocalWindowInsets
import theme.CustomTheme.colors

/**
 * Should be only used on Android and iOS
 */
@Composable
fun BaseScreen(
    topBackgroundColor: Color = colors.backgroundColors.whiteBackgroundColor,
    bottomBackgroundColor: Color = colors.backgroundColors.whiteBackgroundColor,
    screenContent: @Composable () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val windowInsets = LocalWindowInsets.current
        val (statusBar, navigationBar, content) = createRefs()

        val statusBarModifier = Modifier
            .fillMaxWidth()
            .height(windowInsets.topPadding)
            .background(topBackgroundColor)
            .constrainAs(statusBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        Box(modifier = statusBarModifier)

        val navigationBarModifier = Modifier
            .fillMaxWidth()
            .height(windowInsets.bottomPadding)
            .background(bottomBackgroundColor)
            .constrainAs(navigationBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        Box(modifier = navigationBarModifier)

        val screenContentModifier = Modifier.constrainAs(content) {
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(statusBar.bottom)
            bottom.linkTo(navigationBar.top)
        }
        Box(modifier = screenContentModifier) {
            screenContent()
        }
    }
}