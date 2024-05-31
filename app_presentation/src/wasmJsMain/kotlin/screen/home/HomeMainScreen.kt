package screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.CustomTheme.colors

@Composable
internal fun HomeMainScreen() {
    HomeMainScreenContent()
}

/**
 * CONTENT
 */
@Composable
private fun HomeMainScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header()
        Populars()
        SiteInfo()
    }
}

/**
 * HEADER
 */
@Composable
private fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        Toolbar()
        SiteDescription()
        Search()
        Statistic()
    }
}

@Composable
private fun Toolbar() {

}

@Composable
private fun SiteDescription() {

}

@Composable
private fun Search() {

}

@Composable
private fun Statistic() {

}

/**
 * POPULARS
 */
@Composable
private fun Populars() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        PopularTitle()
        PopularList()
    }
}

@Composable
private fun PopularTitle() {

}

@Composable
private fun PopularList() {

}

/**
 * SITE INFO
 */
@Composable
private fun SiteInfo() {
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

@Composable
private fun SiteTitle() {

}

@Composable
private fun SiteInfoList() {

}

@Composable
private fun LegalInfo() {

}