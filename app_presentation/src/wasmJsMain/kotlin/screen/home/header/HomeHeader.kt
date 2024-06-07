package screen.home.header

import BottomShadowView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.image.AppImage
import components.input_field.SearchField
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_app_logo_small
import nowiwr01p.daily.doctor.resources.ic_search
import nowiwr01p.daily.doctor.resources.web_ic_pin
import theme.CustomTheme.colors

@Composable
internal fun Header() = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        Toolbar()
        SiteDescription()
        Search()
        Statistic()
    }
    BottomShadowView()
}

/**
 * TOOLBAR
 */
@Composable
private fun Toolbar() = ConstraintLayout(
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 32.dp)
) {
    val (cityPicker, appLogo, authButton) = createRefs()

    val cityPickerModifier = Modifier.constrainAs(cityPicker) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }
    CityPicker(modifier = cityPickerModifier)

    val appLogoModifier = Modifier
        .size(32.dp)
        .constrainAs(appLogo) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    AppLogo(modifier = appLogoModifier)

    val authButtonModifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(colors.textColors.blueTextColor.copy(alpha = 0.2f))
        .constrainAs(authButton) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    AuthButton(modifier = authButtonModifier)
}

@Composable
private fun CityPicker(modifier: Modifier) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    AppImage(
        image = Res.drawable.web_ic_pin,
        color = colors.textColors.grayTextColor,
        modifier = Modifier.size(24.dp)
    )
    Text(
        text = "Тбилиси",
        style = MaterialTheme.typography.h6,
        color = colors.textColors.blueTextColor.copy(alpha = 0.75f),
        modifier = Modifier.padding(start = 12.dp)
    )
}

@Composable
private fun AppLogo(modifier: Modifier) = AppImage(
    image = Res.drawable.ic_app_logo_small,
    modifier = modifier
)

@Composable
private fun AuthButton(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Войти",
            style = MaterialTheme.typography.h6,
            color = colors.textColors.blueTextColor.copy(alpha = 0.75f),
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp)
        )
    }
}

/**
 * TITLE AND DESCRIPTION
 */
@Composable
private fun SiteDescription() = Text(
    text = "Запись к врачу в вашем городе",
    style = MaterialTheme.typography.h2.copy(textAlign = TextAlign.Center),
    color = colors.textColors.blackTextColor.copy(alpha = 0.9f),
    modifier = Modifier
        .padding(top = 32.dp)
        .fillMaxWidth()
)

@Composable
private fun Search() = ConstraintLayout(
    modifier = Modifier
        .padding(top = 24.dp)
        .fillMaxWidth()
        .height(56.dp)
) {
    val (searchField, searchIcon) = createRefs()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colors.backgroundColors.redBackgroundColor)
            .constrainAs(searchIcon) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
    ) {
        AppImage(
            image = Res.drawable.ic_search,
            color = colors.backgroundColors.whiteBackgroundColor,
            modifier = Modifier.size(28.dp)
        )
    }

    SearchField(
        value = "",
        onValueChange = {},
        placeholderText = "Врачи, клиники, услуги",
        modifier = Modifier
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(colors.backgroundColors.blueBackgroundColor.copy(alpha = 0.5f))
            .constrainAs(searchField) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(searchIcon.start, 16.dp)
            }
    )
}

@Composable
private fun Statistic() = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(top = 0.dp, bottom = 40.dp, start = 16.dp, end = 16.dp) // TODO: Strange behavior with top padding
        .fillMaxWidth()
) {
    StatisticItem(
        count = "4 188 116",
        description = "отзывов"
    )
    StatisticItem(
        count = "17 814 651",
        description = "записей на прием"
    )
    StatisticItem(
        count = "723 118",
        description = "врачей"
    )
    StatisticItem(
        count = "72 561",
        description = "клиник"
    )
    StatisticItem(
        count = "17 475 167",
        description = "посетителей в месяц",
        isLast = true
    )
}

@Composable
private fun StatisticItem(
    count: String,
    description: String,
    isLast: Boolean = false
) {
    ConstraintLayout {
        val (icon, currentStatistic, pnl, endDivider) = createRefs()

        val iconModifier = Modifier
            .size(40.dp)
            .constrainAs(icon) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        AppImage(
            image = Res.drawable.web_ic_pin,
            color = colors.textColors.blueTextColor,
            modifier = iconModifier
        )

        val columnsModifier = Modifier
            .padding(start = 16.dp)
            .constrainAs(currentStatistic) {
                start.linkTo(icon.end)
                top.linkTo(icon.top)
                bottom.linkTo(icon.bottom)
            }
        Column(
            modifier = columnsModifier
        ) {
            Text(
                text = count,
                style = MaterialTheme.typography.h3,
                color = colors.textColors.blackTextColor
            )
            Text(
                text = description,
                style = MaterialTheme.typography.h5.copy(lineHeight = 16.sp),
                color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        val pnlModifier = Modifier
            .padding(top = 12.dp)
            .border(
                width = 2.dp,
                color = colors.backgroundColors.redBackgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .constrainAs(pnl) {
                start.linkTo(icon.start)
                top.linkTo(currentStatistic.bottom)
            }
        Box(
            modifier = pnlModifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+16 343",
                color = colors.backgroundColors.redBackgroundColor,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
            )
        }

        if (!isLast) {
            val endDividerModifier = Modifier
                .padding(start = 24.dp)
                .width(2.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.backgroundColors.grayBackgroundColor.copy(alpha = 0.1f))
                .constrainAs(endDivider) {
                    height = Dimension.fillToConstraints
                    start.linkTo(currentStatistic.end)
                    top.linkTo(currentStatistic.top)
                    bottom.linkTo(currentStatistic.bottom)
                }
            Box(modifier = endDividerModifier)
        }
    }
}