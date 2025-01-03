package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base

interface BaseSubscriptionBenefits: SubscriptionTypeHolder {
    val firstVisitDiscount: SubscriptionBenefitData
    val appointmentsCount: SubscriptionBenefitData
    val chatDescription: SubscriptionBenefitData
    val welcomePaper: SubscriptionBenefitData
    val favoriteClinics: SubscriptionBenefitData
    val decodingAnalysis: SubscriptionBenefitData
    val paidQuestions: SubscriptionBenefitData
}

data class SubscriptionBenefitData(
    val title: String,
    val description: String
)