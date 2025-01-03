package nowiwr01p.daily.doctor.database.init_values.data.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlanUi
import com.nowiwr01p.model.model.subscription.allSubscriptionPlansWithTranslation
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.CommonSubscriptionPlanBenefits
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.FreeSubscriptionPlanBenefits
import nowiwr01p.daily.doctor.database.init_values.domain.subscription.DatabaseInitSubscriptionPlansTableUseCase
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionBenefitsEntity
import nowiwr01p.daily.doctor.database.tables.table.subscription.SubscriptionPlanEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class DatabaseInitSubscriptionPlansTableUseCaseImpl: DatabaseInitSubscriptionPlansTableUseCase {
    /**
     * INIT PLANS
     */
    override suspend fun execute(input: Unit) {
        removeExistingData()
        insertSubscriptionPlans(allSubscriptionPlansWithTranslation)
    }

    /**
     * REMOVE EXISTING DATA
     */
    private fun removeExistingData() = transaction {
        SubscriptionBenefitsEntity.all().forEach { it.delete() }
        SubscriptionPlanEntity.all().forEach { it.delete() }
    }

    /**
     * SUBSCRIPTION PLAN
     */
    private fun insertSubscriptionPlans(allPlans: List<SubscriptionPlanUi>) = transaction {
        allPlans.distinctBy { it.planData.type }.forEach { distinctPlan ->
            val planData = distinctPlan.planData
            val insertedSubscriptionPlan = SubscriptionPlanEntity.new {
                planName = planData.type.name
                monthlyPrice = planData.monthlyPrice
                monthlyPriceDiscounted = planData.monthlyPriceDiscounted
                yearlyPrice = planData.yearlyPrice
                yearlyPriceDiscounted = planData.yearlyPriceDiscounted
            }
            distinctPlan.benefits.forEach { benefits ->
                insertSubscriptionPlanBenefits(
                    benefits = benefits,
                    insertedSubscriptionPlanEntityId = insertedSubscriptionPlan.id
                )
            }
        }
    }

    /**
     * SUBSCRIPTION PLAN BENEFITS
     */
    private fun insertSubscriptionPlanBenefits(
        benefits: SubscriptionBenefits,
        insertedSubscriptionPlanEntityId: EntityID<UUID>
    ) {
        benefits.getBenefitsFromSubscriptionPlan().forEach { benefit ->
            SubscriptionBenefitsEntity.new {
                order = benefit.order
                title = benefit.title
                description = benefit.description
                languageCode = benefits.language.code
                subscriptionPlanId = insertedSubscriptionPlanEntityId
            }
        }
    }

    private fun SubscriptionBenefits.getBenefitsFromSubscriptionPlan() = run {
        when (this) {
            is FreeSubscriptionPlanBenefits -> listOf(freePlanTitleDescription)
            is CommonSubscriptionPlanBenefits -> listOf(
                firstVisitDiscount,
                appointmentsCount,
                chatDescription,
                welcomePaper,
                favoriteClinics,
                decodingAnalysis,
                paidQuestions
            )
        }
    }
}