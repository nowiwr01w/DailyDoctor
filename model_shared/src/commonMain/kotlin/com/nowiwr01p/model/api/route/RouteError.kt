package com.nowiwr01p.model.api.route

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RouteError(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    override val message: String
): Throwable(message = message)