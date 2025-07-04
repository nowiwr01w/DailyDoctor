import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Call Doctor"
    ) {
        val defaultComponentContext = DefaultComponentContext(lifecycle = LifecycleRegistry())
        App().initApplicationUI(defaultComponentContext)
    }
}