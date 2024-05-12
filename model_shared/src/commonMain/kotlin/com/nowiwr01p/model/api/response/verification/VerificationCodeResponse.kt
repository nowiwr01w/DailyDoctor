package com.nowiwr01p.model.api.response.verification

import kotlinx.serialization.Serializable

@Serializable
data class VerificationCodeResponse(
    val code: String
)