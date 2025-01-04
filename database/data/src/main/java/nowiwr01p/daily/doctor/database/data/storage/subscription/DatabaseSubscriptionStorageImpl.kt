package nowiwr01p.daily.doctor.database.data.storage.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.data.storage.BaseDatabaseStorage
import nowiwr01p.daily.doctor.database.domain.storage.subscription.DatabaseSubscriptionStorage
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsEntity
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsTable
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionPlanEntity
import nowiwr01p.daily.doctor.database.tables.table.subscription.toUiModel
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseSubscriptionStorageImpl: BaseDatabaseStorage(), DatabaseSubscriptionStorage {
    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(language: Language) = transaction {
        SubscriptionPlanEntity.all()
            .sortedBy { planEntity ->
                planEntity.monthlyPrice
            }
            .map { planEntity ->
                SubscriptionPlan(
                    subscriptionPlanData = planEntity.toUiModel(),
                    subscriptionPlanBenefits = SubscriptionBenefitsEntity
                        .find {
                            val samePlan = SubscriptionBenefitsTable.subscriptionPlanId eq planEntity.id
                            val sameLanguage = SubscriptionBenefitsTable.languageCode eq language.code
                            samePlan and sameLanguage
                        }
                        .sortedBy { benefitEntity ->
                            benefitEntity.order
                        }
                        .map { benefitEntity ->
                            SubscriptionBenefit(
                                order = benefitEntity.order,
                                title = benefitEntity.title,
                                description = benefitEntity.description
                            )
                        }
                )
            }
    }
}