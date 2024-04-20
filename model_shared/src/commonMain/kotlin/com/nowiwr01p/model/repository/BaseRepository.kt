package com.nowiwr01p.model.repository

abstract class BaseRepository {

    protected fun buildError(message: String? = null): Nothing {
        throw IllegalStateException(message ?: SERVER_ERROR)
    }

    private companion object {
        const val SERVER_ERROR = "Something went wrong"
    }
}