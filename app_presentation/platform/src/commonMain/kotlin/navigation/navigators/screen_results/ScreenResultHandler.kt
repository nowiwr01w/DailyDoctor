package navigation.navigators.screen_results

import navigation.screen_results.ScreenResultKey

interface ScreenResultHandler {
    fun <T: Any> getScreenResult(key: ScreenResultKey<T>, onDataHandled: (T) -> Unit)
    fun <T: Any> setScreenResult(key: ScreenResultKey<T>, data: T)
    fun cancelJob(key: ScreenResultKey<*>)
}
