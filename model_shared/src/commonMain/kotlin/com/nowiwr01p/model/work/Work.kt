package com.nowiwr01p.model.work

import kotlinx.coroutines.Job

abstract class Work {
    protected lateinit var job: Job

    abstract fun startWork()

    protected fun isWorkActive() = job.isActive

    fun endWork() = job.cancel()
}