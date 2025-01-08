package com.nowiwr01p.model.api.errors.onboarding

import com.nowiwr01p.model.api.errors.AppError

sealed class OnboardingApiError(
    override val message: String
): AppError(message) {
    data class LoadOnboardingItemsError(override val message: String): OnboardingApiError(message)
}