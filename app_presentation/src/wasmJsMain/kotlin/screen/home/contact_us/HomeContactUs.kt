package screen.home.contact_us

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.image.AppImage
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_social_facebook_messenger
import nowiwr01p.daily.doctor.resources.ic_social_mail
import nowiwr01p.daily.doctor.resources.ic_social_phone
import nowiwr01p.daily.doctor.resources.ic_social_telegram
import nowiwr01p.daily.doctor.resources.ic_social_whats_app
import org.jetbrains.compose.resources.DrawableResource
import theme.CustomTheme.colors

@Composable
internal fun HomeContactUs() = Column(
    modifier = Modifier.fillMaxWidth()
) {
    Title()
    ContactButtons()
}

@Composable
private fun Title() = Text(
    text = "Есть вопрос по работе сайта или хотите дать обратную связь?\nСвяжитесь с нами!",
    style = MaterialTheme.typography.h2.copy(textAlign = TextAlign.Center),
    color = colors.textColors.blackTextColor.copy(alpha = 0.9f),
    modifier = Modifier
        .padding(top = 56.dp)
        .fillMaxWidth()
)

@Composable
private fun ContactButtons() = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(top = 32.dp, bottom = 64.dp)
        .fillMaxWidth()
) {
    ContactButton(
        icon = Res.drawable.ic_social_telegram,
        backgroundColor = BackgroundColor.Colored(Color(0xFF0088cc)),
        action = {}
    )
    Spacer(
        modifier = Modifier.width(24.dp)
    )
    ContactButton(
        icon = Res.drawable.ic_social_whats_app,
        backgroundColor = BackgroundColor.Colored(Color(0xFF00E676)),
        action = {}
    )
    Spacer(
        modifier = Modifier.width(24.dp)
    )
    ContactButton(
        icon = Res.drawable.ic_social_facebook_messenger,
        backgroundColor = BackgroundColor.Brushed(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFF6968),
                    Color(0xFFA334FA),
                    Color(0xFF0695FF)
                ),
                start = Offset(140.dp.value, 0f),
                end = Offset(0f, 52.dp.value)
            )
        ),
        action = {}
    )
    Spacer(
        modifier = Modifier.width(24.dp)
    )
    ContactButton(
        icon = Res.drawable.ic_social_mail,
        backgroundColor = BackgroundColor.Colored(colors.textColors.blackTextColor),
        action = {}
    )
    Spacer(
        modifier = Modifier.width(24.dp)
    )
    ContactButton(
        icon = Res.drawable.ic_social_phone,
        backgroundColor = BackgroundColor.Colored(Color(0xFF43A047)),
        action = {}
    )
}

@Composable
private fun ContactButton(
    icon: DrawableResource,
    backgroundColor: BackgroundColor,
    action: () -> Unit
) {
    val backgroundModifier = when (backgroundColor) {
        is BackgroundColor.Colored -> Modifier.background(backgroundColor.color)
        is BackgroundColor.Brushed -> Modifier.background(backgroundColor.brush)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(140.dp)
            .height(52.dp)
            .clip(MaterialTheme.shapes.medium)
            .then(backgroundModifier)
            .clickable { action() }
    ) {
        AppImage(
            image = icon,
            color = colors.backgroundColors.whiteBackgroundColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

sealed interface BackgroundColor {
    data class Colored(val color: Color): BackgroundColor
    data class Brushed(val brush: Brush): BackgroundColor
}