package com.nowiwr01p.model.model.subscription.type

import kotlinx.serialization.Serializable

@Serializable
sealed class SubscriptionType(
    val planName: String,
    val monthlyPrice: Double,
    val monthlyPriceDiscounted: Double,
    val yearlyPrice: Double,
    val yearlyPriceDiscounted: Double
) {
    data object Free: SubscriptionType(
        planName = "Free",
        monthlyPrice = 0.0,
        monthlyPriceDiscounted = 0.0,
        yearlyPrice = 0.0,
        yearlyPriceDiscounted = 0.0
    )
    data object Base: SubscriptionType(
        planName = "Base",
        monthlyPrice = 5.49,
        monthlyPriceDiscounted = 3.99,
        yearlyPrice = 24.99,
        yearlyPriceDiscounted = 19.99
    )
    data object Standard: SubscriptionType(
        planName = "Standard",
        monthlyPrice = 9.99,
        monthlyPriceDiscounted = 7.99,
        yearlyPrice = 39.99,
        yearlyPriceDiscounted = 29.99
    )
    data object Premium: SubscriptionType(
        planName = "Premium",
        monthlyPrice = 16.99,
        monthlyPriceDiscounted = 9.99,
        yearlyPrice = 69.99,
        yearlyPriceDiscounted = 59.99
    )
}
