package com.nowiwr01p.model.model.onboarding.item

import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType
import com.nowiwr01p.model.resources.language.Language
import kotlinx.serialization.Serializable

@Serializable
abstract class OnboardingItemsData(
    open val type: OnboardingItemType,
    open val language: Language
) {
    abstract val itemData: OnboardingItemData
}