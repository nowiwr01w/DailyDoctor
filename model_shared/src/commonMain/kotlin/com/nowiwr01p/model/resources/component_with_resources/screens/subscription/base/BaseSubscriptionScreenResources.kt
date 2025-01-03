package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResources
import com.nowiwr01p.model.resources.language.Language

interface BaseSubscriptionScreenResources {
    val brand: BrandConfigType
    val language: Language
    val freePlanResources: FreePlanResources
    val basePlanResources: BasePlanResources
    val standardPlanResources: StandardPlanResources
    val premiumPlanResources: PremiumPlanResources
}