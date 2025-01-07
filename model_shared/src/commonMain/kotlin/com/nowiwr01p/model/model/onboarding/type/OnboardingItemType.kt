package com.nowiwr01p.model.model.onboarding.type

import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.ForWholeFamily
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Notifications
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.OnlineAppointment
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Savings
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.UnlimitedCommunication
import kotlinx.serialization.Serializable

@Serializable
sealed class OnboardingItemType(val position: Int) {
    @Serializable
    data object OnlineAppointment: OnboardingItemType(position = 1)

    @Serializable
    data object UnlimitedCommunication: OnboardingItemType(position = 2)

    @Serializable
    data object ForWholeFamily: OnboardingItemType(position = 3)

    @Serializable
    data object Savings: OnboardingItemType(position = 4)

    @Serializable
    data object Notifications: OnboardingItemType(position = 5)
}

val allOnboardingTypes = listOf(
    OnlineAppointment,
    UnlimitedCommunication,
    ForWholeFamily,
    Savings,
    Notifications
)