package com.nowiwr01p.model.api.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("email")
    override val email: String = "",
    @SerialName("password")
    override val password: String = "",
    @SerialName("captchaCode")
    val captchaCode: String = "",
    @SerialName("agreementVersion")
    val agreementVersion: Int = 0
): BaseAuthRequest