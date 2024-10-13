package com.nowiwr01p.model.repository

import com.nowiwr01p.model.api.errors.AppUiError
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository: KoinComponent {

    private val dispatchers by inject<AppDispatchers>()

    protected suspend fun <T> io(callback: suspend CoroutineScope.() -> T): T {
        return withContext(dispatchers.io) { callback() }
    }

    protected fun buildError(message: String? = null): Nothing {
        throw AppUiError(message ?: SERVER_ERROR)
    }

    private companion object {
        const val SERVER_ERROR = "Something went wrong"
    }
}