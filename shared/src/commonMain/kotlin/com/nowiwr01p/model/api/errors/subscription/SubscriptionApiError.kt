package com.nowiwr01p.model.api.errors.subscription

import com.nowiwr01p.model.api.errors.AppError

sealed class SubscriptionApiError(
    override val message: String
): AppError(message) {
    data class LoadSubscriptionPlansApiError(override val message: String): SubscriptionApiError(message)
}