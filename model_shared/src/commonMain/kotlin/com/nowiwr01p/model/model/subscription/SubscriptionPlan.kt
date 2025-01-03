package com.nowiwr01p.model.model.subscription

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlanData
import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionPlan(
    val subscriptionPlanData: SubscriptionPlanData,
    val subscriptionPlanBenefits: List<SubscriptionBenefit>
)