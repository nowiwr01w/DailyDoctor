package com.nowiwr01p.model.repository

import com.nowiwr01p.model.api.errors.AppUiError

abstract class BaseRepository {

    protected fun buildError(message: String? = null): Nothing {
        throw AppUiError(message ?: SERVER_ERROR)
    }

    private companion object {
        const val SERVER_ERROR = "Something went wrong"
    }
}