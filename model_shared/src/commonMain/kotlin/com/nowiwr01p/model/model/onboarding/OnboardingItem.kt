package com.nowiwr01p.model.model.onboarding

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingItem(
    val type: OnboardingItemType,
    val data: OnboardingItemData
)