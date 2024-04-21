package com.nowiwr01p.model.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("email")
    val email: String = "",
    @SerialName("password")
    val password: String = "",
    @SerialName("captchaCode")
    val captchaCode: String = "",
    @SerialName("agreementVersion")
    val agreementVersion: String = ""
)