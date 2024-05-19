package com.nowiwr01p.model.api.response.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface TokenResponse {
    val token: String
}

@Serializable
data class VerificationTokenResponse(
    @SerialName("token")
    override val token: String
): TokenResponse

@Serializable
data class PinCodeTokenResponse(
    @SerialName("token")
    override val token: String
): TokenResponse

@Serializable
data class AuthBearerTokenResponse(
    @SerialName("token")
    override val token: String
): TokenResponse