import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class App {
    @Composable
    actual fun initApplicationUI(
        context: ComponentContext,
        globalInstanceKeeperOwner: GlobalInstanceKeeperOwner
    ) {
        startLogger()
        App(
            context = context,
            globalInstanceKeeperOwner = globalInstanceKeeperOwner
        )
    }
}
