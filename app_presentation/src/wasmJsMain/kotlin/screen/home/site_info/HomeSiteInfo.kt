package screen.home.site_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.CustomTheme.colors

@Composable
internal fun SiteInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColors.blueBackgroundColor)
    ) {
        SiteTitle()
        SiteInfoList()
        LegalInfo()
    }
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