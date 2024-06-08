package screen.home.site_info

import BottomShadowView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.image.AppImage
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_logo_small
import screen.home.site_info.data.SiteInfoItem
import screen.home.site_info.data.information
import theme.CustomTheme.colors

@Composable
internal fun SiteInfo() = Box(
    modifier = Modifier.fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColors.blueBackgroundColor.copy(alpha = 0.25f))
            .padding(top = 48.dp, bottom = 16.dp)
    ) {
        SiteInfoList()
        LegalInfo()
        CredentialsInfo()
    }
    BottomShadowView() // ContactUs bottom shadow
}

/**
 * SITE INFO LIST
 */
@Composable
private fun SiteInfoList() = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxWidth(0.75f)
) {
    information.forEach { siteInfoItem ->
        SiteInfoItem(siteInfoItem)
    }
}

@Composable
private fun SiteInfoItem(siteInfoItem: SiteInfoItem) = Column {
    Text(
        text = siteInfoItem.title,
        color = colors.textColors.blackTextColor,
        style = MaterialTheme.typography.h4
    )
    Spacer(
        modifier = Modifier.height(16.dp)
    )
    siteInfoItem.items.forEachIndexed { index, infoCategory ->
        Text(
            text = infoCategory,
            color = colors.textColors.blackTextColor,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Normal)
        )
        if (index != siteInfoItem.items.lastIndex) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * LEGAL INFO
 */
@Composable
private fun LegalInfo() = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(top = 48.dp)
        .fillMaxWidth()
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.5.dp,
                shape = MaterialTheme.shapes.medium,
                color = colors.textColors.blackTextColor.copy(alpha = 0.1f)
            )
    ) {
        val warningText = """
            Информация, представленная на сайте, носит рекомендательный характер 
            и не заменяет личный визит врача
        """.trimIndent()
        Text(
            text = warningText,
            color = colors.textColors.blackTextColor,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

/**
 * CREDENTIALS INFO
 */
@Composable
private fun CredentialsInfo() = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .padding(top = 48.dp)
        .fillMaxWidth()
) {
    AppImage(
        image = Res.drawable.ic_app_logo_small,
        modifier = Modifier.size(32.dp)
    )
    Text(
        text = "© OOO \"Мой Доктор\"",
        color = colors.textColors.blackTextColor,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
        modifier = Modifier.padding(top = 12.dp)
    )
    Text(
        text = "powered by Andrey Larionov aka nowiwr01p",
        color = colors.textColors.blackTextColor,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
        modifier = Modifier.padding(top = 8.dp)
    )
}