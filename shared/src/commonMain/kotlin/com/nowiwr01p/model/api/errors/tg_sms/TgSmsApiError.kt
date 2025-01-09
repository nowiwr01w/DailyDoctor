package com.nowiwr01p.model.api.errors.tg_sms

import com.nowiwr01p.model.api.errors.AppError

sealed class TgSmsApiError(
    override val message: String
): AppError(message) {
    data class SendVerificationCodeApiError(override val message: String): TgSmsApiError(message)
}