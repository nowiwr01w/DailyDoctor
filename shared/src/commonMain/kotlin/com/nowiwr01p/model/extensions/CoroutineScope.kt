package com.nowiwr01p.model.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlin.coroutines.cancellation.CancellationException

inline fun <T> CoroutineScope.safeAsync(
    default: T,
    crossinline block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return async {
        try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            default
        }
    }
}