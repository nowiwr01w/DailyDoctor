import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.arkivanov.decompose.ComponentContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class App {
    @Composable
    fun initApplicationUI(
        context: ComponentContext,
        globalInstanceKeeperOwner: GlobalInstanceKeeperOwner = remember { GlobalInstanceKeeperOwner() }
    )
}
