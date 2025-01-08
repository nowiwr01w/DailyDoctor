package com.nowiwr01p.model.model.app_config.settings.server

import kotlinx.serialization.Serializable

@Serializable
data class ServerSettings(
    val serverUrl: String
)