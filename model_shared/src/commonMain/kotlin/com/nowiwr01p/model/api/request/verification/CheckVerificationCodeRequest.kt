package com.nowiwr01p.model.api.request.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckVerificationCodeRequest(
    @SerialName("phone")
    val phone: String,
    @SerialName("code")
    val code: String,
    @SerialName("verificationToken")
    val verificationToken: String
)