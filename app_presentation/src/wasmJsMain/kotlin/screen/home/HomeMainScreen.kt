package screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import screen.home.contact_us.HomeContactUs
import screen.home.header.Header
import screen.home.mobile_app_banner.HomeMobileAppInfo
import screen.home.populars.Populars
import screen.home.site_info.SiteInfo

@Composable
internal fun HomeMainScreen() {
    HomeMainScreenContent()
}

@Composable
private fun HomeMainScreenContent() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item { Header() }
        item { Populars() }
        item { HomeMobileAppInfo() }
        item { HomeContactUs() }
        item { SiteInfo() }
    }
}