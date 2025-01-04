package com.nowiwr01p.model.model.subscription.type

import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType.*
import kotlinx.serialization.Serializable

@Serializable
sealed class SubscriptionPlanType(val name: String) {
    @Serializable
    data object Free: SubscriptionPlanType(name = "Free")

    @Serializable
    data object Base: SubscriptionPlanType(name = "Base")

    @Serializable
    data object Standard: SubscriptionPlanType(name = "Standard")

    @Serializable
    data object Premium: SubscriptionPlanType(name = "Premium")
}

val allSubscriptionTypes = listOf(Free, Base, Standard, Premium)