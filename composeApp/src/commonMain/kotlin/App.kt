import androidx.compose.runtime.Composable
import base.theme.AppTheme
import di.appModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ui.home.HomeMainScreen

@Composable
@Preview
fun App() = withDI(appModules) {
    startLogger()
    AppTheme {
        HomeMainScreen()
    }
}