package com.nowiwr01p.model.work.timer

import com.nowiwr01p.model.work.Work
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class TimerWork: Work() {

    abstract val timerType: TimerType

    private lateinit var coroutineScope: CoroutineScope
    private lateinit var onTickCallback: suspend (Long) -> Unit

    private fun startWork() = coroutineScope.launch {
        val timerPeriod = when (timerType) {
            is TimerType.Up -> (0..timerType.value)
            is TimerType.Down -> (timerType.value downTo 0)
        }
        timerPeriod.asSequence().asFlow()
            .onStart { onStart() }
            .onEach { seconds ->
                onTickCallback(seconds)
                delay(1000)
            }
            .onCompletion {
                delay(1000)
                onCompletion()
            }
            .catch { catchErrors(it) }
            .collect()
    }.let { workJob ->
        job = workJob
    }

    fun startWork(scope: CoroutineScope, onEachSecondCallback: suspend (Long) -> Unit) {
        coroutineScope = scope
        onTickCallback = onEachSecondCallback
        startWork()
    }
}