package com.nowiwr01p.model.api.request.auth

interface BaseAuthRequest {
    val email: String
    val password: String
}