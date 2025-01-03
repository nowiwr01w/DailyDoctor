package com.nowiwr01p.model.model.subscription

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits
import com.nowiwr01p.model.model.subscription.plan.Base
import com.nowiwr01p.model.model.subscription.plan.Free
import com.nowiwr01p.model.model.subscription.plan.Premium
import com.nowiwr01p.model.model.subscription.plan.Standard
import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlanData
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.BasePlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.BasePlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.BasePlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.BasePlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.FreeSubscriptionPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.FreeSubscriptionPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.FreeSubscriptionPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.FreeSubscriptionPlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.PremiumPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.PremiumPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.PremiumPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.PremiumPlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.StandardPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.StandardPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.StandardPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.StandardPlanBenefitsUkrainian
import kotlinx.serialization.Serializable

@Serializable
open class SubscriptionPlanUi(
    open val planData: SubscriptionPlanData,
    open val benefits: List<SubscriptionBenefits>
)

/**
 * FREE
 */
private data object FreeSubscriptionPlanUi: SubscriptionPlanUi(
    planData = Free,
    benefits = listOf(
        FreeSubscriptionPlanBenefitsGeorgian(),
        FreeSubscriptionPlanBenefitsEnglish(),
        FreeSubscriptionPlanBenefitsRussian(),
        FreeSubscriptionPlanBenefitsUkrainian()
    )
)

/**
 * BASE
 */
private data object BaseSubscriptionPlanUi: SubscriptionPlanUi(
    planData = Base,
    benefits = listOf(
        BasePlanBenefitsGeorgian(),
        BasePlanBenefitsEnglish(),
        BasePlanBenefitsRussian(),
        BasePlanBenefitsUkrainian()
    )
)

/**
 * STANDARD
 */
private data object StandardSubscriptionPlanUi: SubscriptionPlanUi(
    planData = Standard,
    benefits = listOf(
        StandardPlanBenefitsGeorgian(),
        StandardPlanBenefitsEnglish(),
        StandardPlanBenefitsRussian(),
        StandardPlanBenefitsUkrainian()
    )
)

/**
 * PREMIUM
 */
private data object PremiumSubscriptionPlanUi: SubscriptionPlanUi(
    planData = Premium,
    benefits = listOf(
        PremiumPlanBenefitsGeorgian(),
        PremiumPlanBenefitsEnglish(),
        PremiumPlanBenefitsRussian(),
        PremiumPlanBenefitsUkrainian()
    )
)

/**
 * ALL
 */
val allSubscriptionPlansWithTranslation = buildList {
    add(FreeSubscriptionPlanUi)
    add(BaseSubscriptionPlanUi)
    add(StandardSubscriptionPlanUi)
    add(PremiumSubscriptionPlanUi)
}