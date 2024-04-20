package com.nowiwr01p.model.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String
)