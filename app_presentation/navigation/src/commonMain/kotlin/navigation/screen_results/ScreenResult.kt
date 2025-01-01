package navigation.screen_results

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import navigation.config.BaseNavigationChild

@Composable
fun <T: Any> BaseNavigationChild.handleScreenResult(
    key: ScreenResultKey<T>,
    handleDataCallback: (T) -> Unit
) {
    DisposableEffect(key) {
        resultHandler.getScreenResult(key, handleDataCallback)
        onDispose {
            resultHandler.cancelJob(key)
        }
    }
}
