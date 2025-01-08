package com.nowiwr01p.model.api.errors.auth

import com.nowiwr01p.model.api.errors.AppError

sealed class AuthApiError(
    override val message: String
): AppError(message) {
    /**
     * SIGN IN
     */
    data class SignInApiError(override val message: String): AuthApiError(message)

    /**
     * SIGN UP
     */
    data class SignUpApiError(override val message: String): AuthApiError(message)
}