package com.nowiwr01p.model.api.errors

import kotlinx.serialization.Serializable

@Serializable
open class AppError(
    override val message: String
): Exception(message)