package com.nowiwr01p.model.api.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("email")
    val email: String,
    @SerialName("timeStamp")
    val timeStamp: Long,
    @SerialName("pinCodeConfirmationToken")
    val pinCodeConfirmationToken: String
)