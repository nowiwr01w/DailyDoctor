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

abstract class TimerWork(
    private val scope: CoroutineScope
): Work() {

    protected abstract val timerType: TimerType

    override fun startWork() = scope.launch {
        val timerPeriod = when (timerType) {
            is TimerType.Up -> (0..timerType.value)
            is TimerType.Down -> (timerType.value downTo 1)
        }
        timerPeriod.asSequence().asFlow()
            .onStart { onStart() }
            .onEach { seconds ->
                delay(1000)
                onEach(seconds)
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
}