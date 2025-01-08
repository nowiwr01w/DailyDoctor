package com.nowiwr01p.model.api.errors.app_config

import com.nowiwr01p.model.api.errors.AppError

sealed class AppConfigApiError(
    override val message: String
): AppError(message) {
    data class LoadAppConfigApiError(override val message: String): AppConfigApiError(message)
}