package com.nowiwr01p.model.api.response.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckVerificationCodeResponse(
    @SerialName("revokeSessionTimestamp")
    val revokeSessionTimestamp: Long
)