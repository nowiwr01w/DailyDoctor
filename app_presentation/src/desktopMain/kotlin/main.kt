import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ui.mobile.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "DailyDoctor") {
        val defaultComponentContext = DefaultComponentContext(lifecycle = LifecycleRegistry())
        App(defaultComponentContext)
    }
}