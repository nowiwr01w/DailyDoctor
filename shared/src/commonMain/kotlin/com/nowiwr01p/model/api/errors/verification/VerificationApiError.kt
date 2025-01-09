package com.nowiwr01p.model.api.errors.verification

import com.nowiwr01p.model.api.errors.AppError

sealed class VerificationApiError(
    override val message: String
): AppError(message) {
    data class SendVerificationCodeApiError(override val message: String): VerificationApiError(message)
    data class CheckVerificationCodeApiError(override val message: String): VerificationApiError(message)
}