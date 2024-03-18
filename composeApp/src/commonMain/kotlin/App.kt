import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.appModules
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ui.home.HomeMainScreen

@Composable
@Preview
fun App() = withDI(appModules) {
    startLogger()
    MaterialTheme {
        HomeMainScreen()
    }
}