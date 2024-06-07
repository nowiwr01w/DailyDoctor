package screen.home.site_info

import BottomShadowView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.CustomTheme.colors

@Composable
internal fun SiteInfo() = Box(
    modifier = Modifier.fillMaxWidth()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(colors.backgroundColors.blueBackgroundColor.copy(alpha = 0.25f))
    ) {
        SiteTitle()
        SiteInfoList()
        LegalInfo()
    }
    BottomShadowView()
}

/**
 * TITLE
 */
@Composable
private fun SiteTitle() {

}

/**
 * SITE INFO LIST
 */
@Composable
private fun SiteInfoList() {

}

/**
 * LEGAL INFO
 */
@Composable
private fun LegalInfo() {

}