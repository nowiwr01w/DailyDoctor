package com.nowiwr01p.model.coroutines.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}