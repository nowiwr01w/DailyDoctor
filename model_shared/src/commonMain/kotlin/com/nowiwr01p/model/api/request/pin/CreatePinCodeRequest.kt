package com.nowiwr01p.model.api.request.pin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePinCodeRequest(
    @SerialName("code")
    val code: String,
    @SerialName("pinCodeToken")
    val pinCodeToken: String
)