package com.nowiwr01p.model.model.onboarding.item

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingItemData(
    val image: String,
    val title: String,
    val description: String,
    val firstButtonText: String,
    val secondButtonText: String
)