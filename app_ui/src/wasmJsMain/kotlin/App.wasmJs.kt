import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import screen.home.HomeMainScreen

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class App {
    @Composable
    actual fun initApplicationUI(
        context: ComponentContext,
        globalInstanceKeeperOwner: GlobalInstanceKeeperOwner
    ) {
        HomeMainScreen()
    }
}
