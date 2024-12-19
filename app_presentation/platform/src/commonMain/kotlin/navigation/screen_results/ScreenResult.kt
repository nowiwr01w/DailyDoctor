package navigation.screen_results

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import navigation.AppNavigator

@Composable
internal fun <T: Any> AppNavigator.handleScreenResult(
    key: ScreenResultKey<T>,
    handleDataCallback: (T) -> Unit
) {
    DisposableEffect(key) {
        onDispose {  }
    }
}
