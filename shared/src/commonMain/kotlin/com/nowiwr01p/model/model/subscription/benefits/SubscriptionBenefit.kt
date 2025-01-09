package com.nowiwr01p.model.model.subscription.benefits

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionBenefit(
    val order: Int,
    val title: String,
    val description: String
)
