package com.nowiwr01p.model.api.request.pin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CheckPinCodeRequest(
    @SerialName("code")
    val code: String,
    @SerialName("checkPinCodeToken")
    val checkPinCodeToken: String = "" // TODO: Remove default value
)