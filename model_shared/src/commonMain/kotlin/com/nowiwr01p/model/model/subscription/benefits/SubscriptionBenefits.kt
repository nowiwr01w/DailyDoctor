package com.nowiwr01p.model.model.subscription.benefits

import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType
import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType.Free
import com.nowiwr01p.model.resources.language.Language
import kotlinx.serialization.Serializable

@Serializable
sealed class SubscriptionBenefits(
    open val type: SubscriptionPlanType,
    open val language: Language
) {
    /**
     * FREE
     */
    abstract class FreeSubscriptionPlanBenefits(
        override val language: Language
    ): SubscriptionBenefits(type = Free, language = language) {
        abstract val freePlanTitleDescription: SubscriptionBenefit
    }
    /**
     * BASE, STANDARD, PREMIUM
     */
    abstract class CommonSubscriptionPlanBenefits(
        override val type: SubscriptionPlanType,
        override val language: Language
    ): SubscriptionBenefits(type = type, language = language) {
        abstract val firstVisitDiscount: SubscriptionBenefit
        abstract val appointmentsCount: SubscriptionBenefit
        abstract val chatDescription: SubscriptionBenefit
        abstract val welcomePaper: SubscriptionBenefit
        abstract val favoriteClinics: SubscriptionBenefit
        abstract val decodingAnalysis: SubscriptionBenefit
        abstract val paidQuestions: SubscriptionBenefit
    }
}