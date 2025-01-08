package com.nowiwr01p.model.model.subscription

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits
import com.nowiwr01p.model.model.subscription.plan.Base
import com.nowiwr01p.model.model.subscription.plan.Free
import com.nowiwr01p.model.model.subscription.plan.Premium
import com.nowiwr01p.model.model.subscription.plan.Standard
import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlanData
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.BasePlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.BasePlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.BasePlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.BasePlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.FreeSubscriptionPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.FreeSubscriptionPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.FreeSubscriptionPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.FreeSubscriptionPlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.PremiumPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.PremiumPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.PremiumPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.PremiumPlanBenefitsUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.StandardPlanBenefitsEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.StandardPlanBenefitsGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.StandardPlanBenefitsRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans.StandardPlanBenefitsUkrainian
import kotlinx.serialization.Serializable

@Serializable
open class SubscriptionPlanServer(
    open val planData: SubscriptionPlanData,
    open val benefits: List<SubscriptionBenefits>
)

/**
 * FREE
 */
private data object FreeSubscriptionPlanServer: SubscriptionPlanServer(
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
private data object BaseSubscriptionPlanServer: SubscriptionPlanServer(
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
private data object StandardSubscriptionPlanServer: SubscriptionPlanServer(
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
private data object PremiumSubscriptionPlanServer: SubscriptionPlanServer(
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
    add(FreeSubscriptionPlanServer)
    add(BaseSubscriptionPlanServer)
    add(StandardSubscriptionPlanServer)
    add(PremiumSubscriptionPlanServer)
}