package com.nowiwr01p.model.api.errors.pin

import com.nowiwr01p.model.api.errors.AppError

sealed class PinApiError(
    override val message: String
): AppError(message) {
    data class CreatePinApiError(override val message: String): PinApiError(message)
    data class CheckPinApiError(override val message: String): PinApiError(message)
    data class ChangePinApiError(override val message: String): PinApiError(message)
    data class DeletePinApiError(override val message: String): PinApiError(message)
}