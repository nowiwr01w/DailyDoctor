package com.nowiwr01p.model.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)