package com.nowiwr01p.model.repository

import com.nowiwr01p.model.api.errors.AppUiError
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import com.nowiwr01p.model.extensions.runCatchingApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository: KoinComponent {

    protected val json by inject<Json>()
    protected val dispatchers by inject<AppDispatchers>()

    protected suspend fun <T> io(callback: suspend CoroutineScope.() -> T): T {
        return withContext(dispatchers.io) { callback() }
    }

    protected inline fun <reified T> getModelFromString(string: String) = runCatchingApp {
        json.decodeFromString<T>(string)
    }.mapCatching { model ->
        model
    }.getOrElse {
        null
    }

    protected fun buildError(message: String? = null): Nothing {
        throw AppUiError(message ?: SERVER_ERROR)
    }

    private companion object {
        const val SERVER_ERROR = "Something went wrong"
    }
}