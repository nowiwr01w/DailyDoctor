package com.nowiwr01p.model.api.request.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVerificationCodeRequest(
    @SerialName("phone")
    val phone: String
)