import androidx.compose.ui.window.ComposeUIViewController
import navigation.MainNavigatorImpl

fun MainViewController() = ComposeUIViewController {
    App(MainNavigatorImpl())
}