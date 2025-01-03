package nowiwr01p.daily.doctor.database.data.storage.subscription

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.subscription.plan.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlan
import nowiwr01p.daily.doctor.database.data.storage.BaseDatabaseStorage
import nowiwr01p.daily.doctor.database.domain.storage.subscription.DatabaseSubscriptionStorage
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsTable
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsEntity
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionPlanEntity
import com.nowiwr01p.model.resources.language.Language
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseSubscriptionStorageImpl: BaseDatabaseStorage(), DatabaseSubscriptionStorage {
    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(brand: BrandConfigType, language: Language) = transaction {
        SubscriptionPlanEntity.all()
            .filter { planEntity ->
                val sameBrand = planEntity.brand.brandName == brand.type
                val sameLanguage = planEntity.languageCode == language.code
                sameBrand && sameLanguage
            }
            .sortedBy { planEntity ->
                planEntity.monthlyPrice
            }
            .map { planEntity ->
                val benefits = SubscriptionBenefitsEntity
                    .find {
                        SubscriptionBenefitsTable.subscriptionPlanId eq planEntity.id
                    }
                    .map { benefitEntity ->
                        SubscriptionBenefit(
                            title = benefitEntity.title,
                            description = benefitEntity.description
                        )
                    }
                SubscriptionPlan(
                    monthlyPrice = planEntity.monthlyPrice,
                    monthlyPriceDiscounted = planEntity.monthlyPriceDiscounted,
                    yearlyPrice = planEntity.yearlyPrice,
                    yearlyPriceDiscounted = planEntity.yearlyPriceDiscounted,
                    benefits = benefits,
                    languageCode = planEntity.languageCode,
                    brand = brand.type
                )
            }
    }
}