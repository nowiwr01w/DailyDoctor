package com.nowiwr01p.model.api.errors

import com.nowiwr01p.model.api.errors.HttpClientError.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppUiError(
    @SerialName("errorMessage")
    val errorMessage: String
): ExpectedError(message = errorMessage)
