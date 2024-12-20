package navigation.screen_results

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import navigation.AppNavigator

@Composable
internal fun <T: Any> navigation.AppNavigator.handleScreenResult(
    key: navigation.screen_results.ScreenResultKey<T>,
    handleDataCallback: (T) -> Unit
) {
    DisposableEffect(key) {
        onDispose {  }
    }
}
