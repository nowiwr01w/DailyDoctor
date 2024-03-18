package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.rememberViewModel
import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.ic_app_logo_big
import dailydoctor.composeapp.generated.resources.ic_pin
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HomeMainScreen(
    viewModel: HomeViewModel = rememberViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(0.75f)
                .fillMaxHeight()
        ) {
            Toolbar()
            Description()
        }
    }
}

@Composable
private fun Toolbar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        CityPicker()
        Spacer(modifier = Modifier.weight(1f))
        AppLogo()
        Spacer(modifier = Modifier.weight(1f))
        AuthButton()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CityPicker() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_pin),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Москва",
            color = Color(0xFF508eef),
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AppLogo() = Image(
    painter = painterResource(Res.drawable.ic_app_logo_big),
    contentDescription = "",
    modifier = Modifier.size(256.dp)
)

@Composable
private fun AuthButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF508eef).copy(alpha = 0.25f))
            .clickable {  }
    ) {
        Text(
            text = "Войти",
            color = Color(0xFF508eef),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 18.dp)
        )
    }
}

@Composable
private fun Description() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 48.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Сайт отзывов о врачах №1 в Грузии",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 28.sp,
            color = Color.Black
        )
        Text(
            text = "по количеству отзывов, посетителей и страниц врачей\n(исследование РБК, сентябрь 2019)",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}