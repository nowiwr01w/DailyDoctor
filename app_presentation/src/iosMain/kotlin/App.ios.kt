import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.nowiwr01p.model.di.initKoin
import di.appModules

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class App {
    @Composable
    actual fun initApplicationUI(context: ComponentContext) {
        startKoin()()
        App(context)
    }

    private fun startKoin(): @Composable () -> Unit = { initKoin(appModules) }
}