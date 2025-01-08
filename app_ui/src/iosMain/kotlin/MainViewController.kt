import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import com.nowiwr01p.model.di.initKoin
import di.appModules

fun MainViewController() = ComposeUIViewController {
    initKoin(appModules)
    val defaultComponentContext = DefaultComponentContext(ApplicationLifecycle())
    App().initApplicationUI(defaultComponentContext)
}