package com.nowiwr01p.model.api.errors

import kotlinx.serialization.Serializable

sealed interface HttpClientError {

    @Serializable
    data class NoErrorExpected(
        val route: String,
        val errorMessage: String,
    ): ExpectedError("Unexpected error, please contact us.")

    @Serializable
    open class ExpectedError(
        override val message: String
    ): HttpClientError, Throwable(message)

    @Serializable
    data class UnexpectedError(
        override val message: String
    ): HttpClientError, Throwable(message)
}