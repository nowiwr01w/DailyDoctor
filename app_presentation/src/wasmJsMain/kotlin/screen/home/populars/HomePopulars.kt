package screen.home.populars

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import theme.CustomTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import components.button.StateButton
import components.image.AppImage
import components.text.DashedUnderlineText
import extensions.advancedShadow
import getScreenWidth
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_fire
import screen.home.populars.data.Category
import screen.home.populars.data.ClinicsCategory
import screen.home.populars.data.DoctorsCategory
import screen.home.populars.data.ProceduresCategory
import screen.home.populars.data.ServicesCategory
import screen.home.populars.data.categories
import theme.CustomTheme.colors

@Composable
internal fun Populars() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        PopularTitle()
        PopularList()
    }
}

/**
 * TITLE
 */
@Composable
private fun PopularTitle() = Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .padding(top = 16.dp)
        .fillMaxWidth()
) {
    Box {
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(30.dp)
                .graphicsLayer { rotationX = 45f }
                .background(
                    shape = CircleShape,
                    brush = Brush.radialGradient(
                        colors = listOf(
                            colors.backgroundColors.redBackgroundColor.copy(0.25f),
                            colors.backgroundColors.redBackgroundColor.copy(0.15f),
                            Color.Transparent
                        )
                    )
                )
        )
        AppImage(
            image = Res.drawable.ic_fire,
            color = colors.backgroundColors.redBackgroundColor,
            modifier = Modifier
                .padding(start = 5.dp)
                .size(20.dp)
        )
    }
    DashedUnderlineText(
        prefix = "Популярное в ",
        highlightedText = "Тбилиси",
        modifier = Modifier.padding(start = 8.dp, bottom = 5.dp)
    )
}

/**
 * LIST
 */
@Composable
private fun PopularList() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
    ) {
        categories.forEachIndexed { index, category ->
            PopularItem(category)
            if (index != categories.lastIndex) {
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(64.dp))
}

@Composable
private fun PopularItem(category: Category) {
    val maxItemWidth = (0.7f * getScreenWidth() - 3 * 24.dp) / 4
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .widthIn(max = maxItemWidth)
            .advancedShadow()
            .clip(CustomTheme.shapes.medium)
            .background(
                shape = CustomTheme.shapes.medium,
                color = colors.backgroundColors.whiteBackgroundColor
            )
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
            PopularRowItem(
                name = category.name,
                count = category.value,
                textStyle = CustomTheme.typography.headlineLarge,
                textColor = colors.textColors.blackTextColor
            )
            Box(modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(2.dp)
                .clip(CustomTheme.shapes.small)
                .background(colors.backgroundColors.grayBackgroundColor.copy(alpha = 0.1f))
            )
            category.categories.forEachIndexed { index, categoryData ->
                PopularRowItem(
                    name = categoryData.name,
                    count = categoryData.value.toString(),
                    textStyle = CustomTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Normal),
                    textColor = colors.textColors.blackTextColor.copy(alpha = 0.5f)
                )
                Spacer(
                    modifier = Modifier.height(if (index == category.categories.lastIndex) 32.dp else 20.dp)
                )
            }
            StateButton(
                text = when (category) {
                    is DoctorsCategory -> "Все врачи"
                    is ClinicsCategory -> "Все клиники"
                    is ServicesCategory -> "Все услуги"
                    is ProceduresCategory -> "Все процедуры"
                    else -> "Подробнее"
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }
}

@Composable
private fun PopularRowItem(
    name: String,
    count: String,
    textStyle: TextStyle,
    textColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = textStyle,
            color = textColor
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = count,
            style = textStyle,
            color = textColor
        )
    }
}
