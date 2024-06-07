package screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import components.divider.SinusoidalDivider
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header()
        Populars()
        SinusoidalDivider(modifier = Modifier.padding(top = 16.dp))
        HomeMobileAppInfo()
        SinusoidalDivider(
            modifier = Modifier
                .padding(top = 48.dp)
                .graphicsLayer { scaleY = -1f }
        )
        SiteInfo()
        Spacer(modifier = Modifier.height(64.dp))
    }
}