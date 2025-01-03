package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.app_config.config.BrandConfigType.CALL_DOCTOR_CONFIG_TYPE
import com.nowiwr01p.model.resources.component_with_resources.base.ComponentResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResourcesEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResourcesGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResourcesRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.BasePlanResourcesUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResourcesEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResourcesGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResourcesRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResourcesUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResourcesEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResourcesGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResourcesRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.PremiumPlanResourcesUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResourcesEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResourcesGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResourcesRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.StandardPlanResourcesUkrainian
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.Language.English
import com.nowiwr01p.model.resources.language.Language.Georgian
import com.nowiwr01p.model.resources.language.Language.Russian
import com.nowiwr01p.model.resources.language.Language.Ukrainian

/**
 * DATA
 */
sealed class SubscriptionScreenResourcesCallDoctor: BaseSubscriptionScreenResources, ComponentResources {
    override val brand: BrandConfigType = CALL_DOCTOR_CONFIG_TYPE
}

/**
 * TRANSLATIONS
 */
internal data class SubscriptionScreenResourcesCallDoctorGeorgian(
    override val language: Language = Georgian,
    override val freePlanResources: FreePlanResources = FreePlanResourcesGeorgian(),
    override val basePlanResources: BasePlanResources = BasePlanResourcesGeorgian(),
    override val standardPlanResources: StandardPlanResources = StandardPlanResourcesGeorgian(),
    override val premiumPlanResources: PremiumPlanResources = PremiumPlanResourcesGeorgian()
): SubscriptionScreenResourcesCallDoctor()

internal data class SubscriptionScreenResourcesCallDoctorEnglish(
    override val language: Language = English,
    override val freePlanResources: FreePlanResources = FreePlanResourcesEnglish(),
    override val basePlanResources: BasePlanResources = BasePlanResourcesEnglish(),
    override val standardPlanResources: StandardPlanResources = StandardPlanResourcesEnglish(),
    override val premiumPlanResources: PremiumPlanResources = PremiumPlanResourcesEnglish()
): SubscriptionScreenResourcesCallDoctor()

internal data class SubscriptionScreenResourcesCallDoctorRussian(
    override val language: Language = Russian,
    override val freePlanResources: FreePlanResources = FreePlanResourcesRussian(),
    override val basePlanResources: BasePlanResources = BasePlanResourcesRussian(),
    override val standardPlanResources: StandardPlanResources = StandardPlanResourcesRussian(),
    override val premiumPlanResources: PremiumPlanResources = PremiumPlanResourcesRussian()
): SubscriptionScreenResourcesCallDoctor()

internal data class SubscriptionScreenResourcesCallDoctorUkrainian(
    override val language: Language = Ukrainian,
    override val freePlanResources: FreePlanResources = FreePlanResourcesUkrainian(),
    override val basePlanResources: BasePlanResources = BasePlanResourcesUkrainian(),
    override val standardPlanResources: StandardPlanResources = StandardPlanResourcesUkrainian(),
    override val premiumPlanResources: PremiumPlanResources = PremiumPlanResourcesUkrainian()
): SubscriptionScreenResourcesCallDoctor()

/**
 * ALL BRAND RESOURCES
 */
internal fun getSubscriptionResourcesCallDoctor() = buildList<BaseSubscriptionScreenResources> {
    val resources = listOf(
        SubscriptionScreenResourcesCallDoctorGeorgian(),
        SubscriptionScreenResourcesCallDoctorEnglish(),
        SubscriptionScreenResourcesCallDoctorRussian(),
        SubscriptionScreenResourcesCallDoctorUkrainian()
    )
    addAll(resources)
}