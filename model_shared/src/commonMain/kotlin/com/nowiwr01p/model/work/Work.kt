package com.nowiwr01p.model.work

import kotlinx.coroutines.Job

abstract class Work {

    protected lateinit var job: Job

    abstract fun startWork()
    protected abstract suspend fun onEach(seconds: Long)

    protected fun isWorkActive() = job.isActive

    protected open suspend fun onStart() {}
    protected open suspend fun onCompletion() {}
    protected open suspend fun catchErrors(error: Throwable) {}

    fun endWork() = job.cancel()
}