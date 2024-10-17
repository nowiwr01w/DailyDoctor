package com.nowiwr01p.model.api.request.auth

interface BaseAuthRequest {
    val phone: String
    val password: String
    val captchaCode: String
}