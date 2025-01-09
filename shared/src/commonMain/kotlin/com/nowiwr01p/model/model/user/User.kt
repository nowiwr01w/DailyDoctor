package com.nowiwr01p.model.model.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String = "huj",
    val phone: String = "",
    val password: String = "",
    val pinCodeToken: String = "",
    val agreementVersion: Int = 0,
    val isPhoneVerified: Boolean = false
)