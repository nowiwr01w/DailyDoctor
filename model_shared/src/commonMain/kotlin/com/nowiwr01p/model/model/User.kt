package com.nowiwr01p.model.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val password: String,
    val pinCodeToken: String,
    val agreementVersion: Int,
    val isEmailVerified: Boolean
)