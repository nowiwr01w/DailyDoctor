package com.nowiwr01p.model.api.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("phone")
    override val phone: String = "",
    @SerialName("password")
    override val password: String = "",
    @SerialName("captchaCode")
    override val captchaCode: String = ""
): BaseAuthRequest