package com.nowiwr01p.model.api.response.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse(
    @SerialName("authToken")
    val authToken: String
)