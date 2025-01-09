package navigation.navigators.screen_results

import navigation.screen_results.ScreenResultKey

interface ScreenResultHandler {
    fun <T: Any> getScreenResult(key: navigation.screen_results.ScreenResultKey<T>, onDataHandled: (T) -> Unit)
    fun <T: Any> setScreenResult(key: navigation.screen_results.ScreenResultKey<T>, data: T)
    fun cancelJob(key: navigation.screen_results.ScreenResultKey<*>)
}
