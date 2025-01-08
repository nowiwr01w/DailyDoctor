package com.nowiwr01p.model.api.errors

open class AppError(
    override val message: String
): Exception(message)