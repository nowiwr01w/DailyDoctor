import androidx.compose.runtime.Composable
import base.theme.AppTheme
import di.appModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ui.web.home.HomeMainScreenWeb

@Composable
@Preview
fun App() = withDI(appModules) {
    startLogger()
    AppTheme {
        HomeMainScreenWeb()
    }
}