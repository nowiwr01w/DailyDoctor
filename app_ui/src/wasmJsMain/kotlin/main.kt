import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.nowiwr01p.model.di.initKoin
import di.appModules

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        initKoin(appModules)
        val defaultComponentContext = DefaultComponentContext(lifecycle = LifecycleRegistry())
        App().initApplicationUI(defaultComponentContext)
    }
}