package domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}