package com.nowiwr01p.model.work.periodic

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.time.TimeInSeconds
import com.nowiwr01p.model.work.Work
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class PeriodicWork: Work(), KoinComponent {

    private val appScope by inject<AppScope>()

    protected abstract val periodType: TimeInSeconds
    protected abstract suspend fun onEach(seconds: Long)

    fun startWork() = appScope.scope.launch {
        (0..Long.MAX_VALUE).asSequence().asFlow()
            .onStart { onStart() }
            .onEach { seconds ->
                onEach(seconds)
                delay(periodType.toMillis())
            }
            .onCompletion { onCompletion() }
            .catch { catchErrors(it) }
            .collect()
    }.let { workJob ->
        job = workJob
    }
}