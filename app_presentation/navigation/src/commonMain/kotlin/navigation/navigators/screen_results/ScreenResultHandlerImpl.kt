package navigation.navigators.screen_results

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import navigation.screen_results.ScreenResultKey

class ScreenResultHandlerImpl(
    private val appScope: AppScope,
    private val dispatchers: AppDispatchers
): ScreenResultHandler {
    /**
     * DATA
     */
    private val mutex = Mutex()
    private val jobsByKey = mutableMapOf<ScreenResultKey<*>, Job>()
    private val screensResultsData = mutableMapOf<ScreenResultKey<*>, MutableSharedFlow<Any>>()

    /**
     * GET
     */
    override fun <T: Any> getScreenResult(key: ScreenResultKey<T>, onDataHandled: (T) -> Unit) {
        val job = appScope.scope.launch(dispatchers.io) {
            val sharedFlow = mutex.withLock {
                screensResultsData.getOrPut(key) { MutableSharedFlow(replay = 1) }
            }
            sharedFlow.collect { value ->
                @Suppress("UNCHECKED_CAST")
                // As [ScreenResultKey] type is <T> that's always correct
                onDataHandled(value as T)
            }
        }
        appScope.scope.launch(dispatchers.io) {
            mutex.withLock {
                jobsByKey[key] = job
            }
        }
    }

    /**
     * SET
     */
    override fun <T: Any> setScreenResult(key: ScreenResultKey<T>, data: T) {
        appScope.scope.launch(dispatchers.io) {
            val sharedFlow = mutex.withLock {
                screensResultsData.getOrPut(key) { MutableSharedFlow(replay = 1) }
            }
            sharedFlow.emit(data)
        }
    }

    /**
     * CANCEL
     */
    override fun cancelJob(key: ScreenResultKey<*>) {
        appScope.scope.launch(dispatchers.io) {
            mutex.withLock {
                jobsByKey.remove(key)?.cancel()
            }
        }
    }
}
