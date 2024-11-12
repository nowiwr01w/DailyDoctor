package com.nowiwr01p.model.model.onboarding

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingItem(
    val image: String,
    val title: String,
    val description: String,
    val firstButtonText: String,
    val secondButtonText: String
)