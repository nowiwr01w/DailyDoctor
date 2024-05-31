package screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import components.image.AppImage
import components.input_field.SearchField
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_search
import nowiwr01p.daily.doctor.resources.web_ic_pin
import theme.CustomTheme
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
            .fillMaxWidth(0.8f)
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        Toolbar()
        SiteDescription()
        Search()
        Statistic()
    }
}

/**
 * TOOLBAR
 */
@Composable
private fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        CityPicker()
        Spacer(modifier = Modifier.weight(1f))
        AppTextLogo()
        Spacer(modifier = Modifier.weight(1f))
        AuthButton()
    }
}

@Composable
private fun CityPicker() = Row(
    verticalAlignment = Alignment.CenterVertically
) {
    AppImage(
        image = Res.drawable.web_ic_pin,
        color = colors.textColors.grayTextColor,
        modifier = Modifier.size(24.dp)
    )
    Text(
        text = "Москва",
        style = MaterialTheme.typography.h6,
        color = colors.textColors.blueTextColor,
        modifier = Modifier.padding(start = 12.dp)
    )
}

@Composable
private fun AppTextLogo() = Row {
    val textStyle = MaterialTheme.typography.h3.copy(
        letterSpacing = 1.5.sp,
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        text = "Daily".uppercase(),
        style = textStyle,
        color = colors.textColors.redTextColor
    )
    Text(
        text = "Doctor".uppercase(),
        style = textStyle,
        color = colors.textColors.blueTextColor
    )
}

@Composable
private fun AuthButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.textColors.blueTextColor.copy(alpha = 0.3f))
    ) {
        Text(
            text = "Войти",
            style = MaterialTheme.typography.h6,
            color = colors.textColors.blueTextColor,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp)
        )
    }
}

/**
 * TITLE AND DESCRIPTION
 */
@Composable
private fun SiteDescription() = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 64.dp)
) {
    Text(
        text = "Сайт отзывов о врачах №1 в России",
        style = MaterialTheme.typography.h2.copy(
//            letterSpacing = 1.1.sp
        ),
        color = colors.textColors.blackTextColor.copy(alpha = 0.9f)
    )
    Text(
        text = "по количеству отзывов, посетителей и страниц врачей\n" + "(исследование РБК, сентябрь 2019)",
        style = MaterialTheme.typography.h5.copy(
//            letterSpacing = 1.1.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center
        ),
        color = colors.textColors.blackTextColor.copy(alpha = 0.5f)
    )
}

@Composable
private fun Search() = ConstraintLayout(
    modifier = Modifier
        .padding(top = 64.dp)
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