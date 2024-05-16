package com.nowiwr01p.model.api.response.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVerificationCodeResponse(
    @SerialName("id")
    val id: String // TODO: Change to VerificationToken
)