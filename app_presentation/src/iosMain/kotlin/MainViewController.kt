import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle

fun MainViewController() = ComposeUIViewController {
    val defaultComponentContext = DefaultComponentContext(ApplicationLifecycle())
    App(defaultComponentContext)
}