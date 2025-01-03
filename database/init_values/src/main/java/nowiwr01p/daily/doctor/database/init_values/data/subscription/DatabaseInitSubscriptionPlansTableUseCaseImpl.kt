package nowiwr01p.daily.doctor.database.init_values.data.subscription

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionBenefits
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionScreenResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources.FreePlanResources
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.getSubscriptionResourcesForAllBrands
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.init_values.domain.subscription.DatabaseInitSubscriptionPlansTableUseCase
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandEntity
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsEntity
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionPlanEntity
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseInitSubscriptionPlansTableUseCaseImpl: DatabaseInitSubscriptionPlansTableUseCase {
    /**
     * INIT PLANS
     */
    override suspend fun execute(input: Unit) {
        removeExistingData()
        buildRelevantData()
    }

    /**
     * REMOVE EXISTING DATA
     */
    private fun removeExistingData() = transaction {
        SubscriptionBenefitsEntity.all().forEach { it.delete() }
        SubscriptionPlanEntity.all().forEach { it.delete() }
    }

    /**
     * RELEVANT DATA
     */
    private fun buildRelevantData() = getSubscriptionResourcesForAllBrands().forEach { allBrandsResources ->
        allBrandsResources.forEach { brandResources ->
            brandResources.getSubscriptionPlansFromBrand().forEach { brandSubscriptionPlans ->
                when (brandSubscriptionPlans) {
                    is FreePlanResources -> {
                        // TODO: Handle Free plan
                    }
                    is BaseSubscriptionBenefits -> insertSubscriptionPlan(
                        brandId = brandResources.brand.getBrandEntity(),
                        language = brandResources.language,
                        resources = brandSubscriptionPlans
                    )
                }
            }
        }
    }

    private fun BaseSubscriptionScreenResources.getSubscriptionPlansFromBrand() = listOf(
        freePlanResources,
        basePlanResources,
        standardPlanResources,
        premiumPlanResources
    )

    private fun BrandConfigType.getBrandEntity() = transaction {
        BrandEntity.find { BrandTable.brandName eq type }.single()
    }

    /**
     * SUBSCRIPTION PLAN
     */
    private fun insertSubscriptionPlan(
        brandId: BrandEntity,
        language: Language,
        resources: BaseSubscriptionBenefits
    ) {
        transaction {
            val subscriptionPlanEntity = SubscriptionPlanEntity.new {
                brand = brandId
                planName = resources.type.planName
                languageCode = language.code
                monthlyPrice = resources.type.monthlyPrice
                monthlyPriceDiscounted = resources.type.monthlyPriceDiscounted
                yearlyPrice = resources.type.yearlyPrice
                yearlyPriceDiscounted = resources.type.yearlyPriceDiscounted
            }
            insertSubscriptionPlanBenefits(
                subscriptionPlanEntity = subscriptionPlanEntity,
                language = language,
                resources = resources
            )
        }
    }

    /**
     * SUBSCRIPTION PLAN BENEFITS
     */
    private fun insertSubscriptionPlanBenefits(
        subscriptionPlanEntity: SubscriptionPlanEntity,
        language: Language,
        resources: BaseSubscriptionBenefits
    ) {
        resources.getBenefitsFromSubscriptionPlan().forEach { resource ->
            SubscriptionBenefitsEntity.new {
                brand = subscriptionPlanEntity.brand
                languageCode = language.code
                subscriptionPlanId = subscriptionPlanEntity.id
                title = resource.title
                description = resource.description
            }
        }
    }

    private fun BaseSubscriptionBenefits.getBenefitsFromSubscriptionPlan() = listOf(
        firstVisitDiscount,
        appointmentsCount,
        chatDescription,
        welcomePaper,
        favoriteClinics,
        decodingAnalysis,
        paidQuestions
    )
}