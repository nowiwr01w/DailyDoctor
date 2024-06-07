package screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import screen.home.header.Header
import screen.home.populars.Populars
import screen.home.site_info.SiteInfo

@Composable
internal fun HomeMainScreen() {
    HomeMainScreenContent()
}

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