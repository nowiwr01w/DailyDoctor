package com.nowiwr01p.model.model.subscription.plan

import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType
import kotlinx.serialization.Serializable

@Serializable
open class SubscriptionPlanData(
    val type: SubscriptionPlanType,
    val monthlyPrice: Double,
    val monthlyPriceDiscounted: Double,
    val yearlyPrice: Double,
    val yearlyPriceDiscounted: Double
)

@Serializable
internal data object Free: SubscriptionPlanData(
    type = SubscriptionPlanType.Free,
    monthlyPrice = 0.0,
    monthlyPriceDiscounted = 0.0,
    yearlyPrice = 0.0,
    yearlyPriceDiscounted = 0.0
)

@Serializable
internal data object Base: SubscriptionPlanData(
    type = SubscriptionPlanType.Base,
    monthlyPrice = 5.49,
    monthlyPriceDiscounted = 3.99,
    yearlyPrice = 24.99,
    yearlyPriceDiscounted = 19.99
)

@Serializable
internal data object Standard: SubscriptionPlanData(
    type = SubscriptionPlanType.Standard,
    monthlyPrice = 9.99,
    monthlyPriceDiscounted = 7.99,
    yearlyPrice = 39.99,
    yearlyPriceDiscounted = 29.99
)

@Serializable
internal data object Premium: SubscriptionPlanData(
    type = SubscriptionPlanType.Premium,
    monthlyPrice = 16.99,
    monthlyPriceDiscounted = 9.99,
    yearlyPrice = 69.99,
    yearlyPriceDiscounted = 59.99
)

val allSubscriptionPlansData = listOf(Free, Base, Standard, Premium)