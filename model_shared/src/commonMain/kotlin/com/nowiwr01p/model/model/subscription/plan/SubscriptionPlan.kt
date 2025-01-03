package com.nowiwr01p.model.model.subscription.plan

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionPlan(
    val monthlyPrice: Double,
    val monthlyPriceDiscounted: Double,
    val yearlyPrice: Double,
    val yearlyPriceDiscounted: Double,
    val benefits: List<SubscriptionBenefit>,
    val languageCode: String,
    val brand: String,
    val isSelected: Boolean = false
)

@Serializable
data class SubscriptionBenefit(
    val title: String,
    val description: String
)