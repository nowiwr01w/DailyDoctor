package com.nowiwr01p.model.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("name")
    val name: String,
    @SerialName("token")
    val token: String
)