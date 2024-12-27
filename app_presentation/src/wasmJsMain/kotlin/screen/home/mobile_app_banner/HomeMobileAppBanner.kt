package screen.home.mobile_app_banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import theme.CustomTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.divider.SinusoidalDivider
import components.image.AppImage
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_apple_logo
import nowiwr01p.daily.doctor.resources.ic_google_play_logo
import nowiwr01p.daily.doctor.resources.qr_code
import org.jetbrains.compose.resources.DrawableResource
import screen.home.mobile_app_banner.AppMarket.AppStore
import screen.home.mobile_app_banner.AppMarket.GooglePlay
import theme.CustomTheme.colors

@Composable
internal fun HomeMobileAppInfo() = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    SinusoidalDivider(
        modifier = Modifier.padding(top = 16.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            QrWithButton(GooglePlay)
            Spacer(modifier = Modifier.width(48.dp))
            QrWithButton(AppStore)
        }
    }
    SinusoidalDivider(
        modifier = Modifier
            .padding(top = 48.dp)
            .graphicsLayer { scaleY = -1f }
    )
}

@Composable
private fun Title(modifier: Modifier) = Text(
    text = "Клиники и врачи всегда под рукой\nв мобильном приложении",
    style = CustomTheme.typography.displayMedium.copy(textAlign = TextAlign.Center),
    color = colors.textColors.blackTextColor.copy(alpha = 0.9f),
    modifier = modifier
)

@Composable
private fun QrWithButton(appMarket: AppMarket) = Column(
    horizontalAlignment = Alignment.CenterHorizontally
) {
    AppImage(
        image = Res.drawable.qr_code,
        modifier = Modifier.size(256.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(CustomTheme.shapes.small)
            .background(
                shape = CustomTheme.shapes.small,
                color = colors.textColors.blackTextColor
            )
            .padding(vertical = 8.dp, horizontal = 10.dp)
    ) {
        AppImage(
            image = appMarket.logo,
            color = when (appMarket) {
                is AppStore -> colors.backgroundColors.whiteBackgroundColor
                is GooglePlay -> null
            },
            modifier = Modifier.size(36.dp)
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = "Загрузите в",
                color = colors.textColors.whiteTextColor,
                style = CustomTheme.typography.bodySmall.copy(letterSpacing = 1.25.sp)
            )
            Text(
                text = appMarket.name,
                color = colors.textColors.whiteTextColor,
                style = CustomTheme.typography.headlineLarge.copy(letterSpacing = 1.25.sp)
            )
        }
    }
    Text(
        text = appMarket.versions,
        style = CustomTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium),
        color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
        modifier = Modifier.padding(top = 12.dp)
    )
}

private sealed class AppMarket(
    val name: String,
    val logo: DrawableResource,
    val versions: String
) {
    data object AppStore: AppMarket(
        name = "App Store  ", // Do not remove whitespaces
        logo = Res.drawable.ic_apple_logo,
        versions = "iOS 13 и выше"
    )
    data object GooglePlay: AppMarket(
        name = "Google Play",
        logo = Res.drawable.ic_google_play_logo,
        versions = "Android 8 и выше"
    )
}
