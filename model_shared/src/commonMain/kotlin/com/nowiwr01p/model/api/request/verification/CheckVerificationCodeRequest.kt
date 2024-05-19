package com.nowiwr01p.model.api.request.verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckVerificationCodeRequest(
    @SerialName("code")
    val code: String,
    @SerialName("email")
    val email: String
)