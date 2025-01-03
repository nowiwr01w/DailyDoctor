package nowiwr01p.daily.doctor.database.tables.table.subscription

import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlanData
import com.nowiwr01p.model.model.subscription.plan.allSubscriptionPlansData
import com.nowiwr01p.model.model.subscription.type.allSubscriptionTypes
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object SubscriptionPlanTable: UUIDTable("subscription_plans") {
    val planName = varchar("plan_name", 32)
    val monthlyPrice = double("monthlyPrice")
    val monthlyPriceDiscounted = double("monthlyPriceDiscounted")
    val yearlyPrice = double("yearlyPrice")
    val yearlyPriceDiscounted = double("yearlyPriceDiscounted")
}

class SubscriptionPlanEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var planName by SubscriptionPlanTable.planName
    var monthlyPrice by SubscriptionPlanTable.monthlyPrice
    var monthlyPriceDiscounted by SubscriptionPlanTable.monthlyPriceDiscounted
    var yearlyPrice by SubscriptionPlanTable.yearlyPrice
    var yearlyPriceDiscounted by SubscriptionPlanTable.yearlyPriceDiscounted

    companion object: UUIDEntityClass<SubscriptionPlanEntity>(SubscriptionPlanTable)
}

fun SubscriptionPlanEntity.toUiModel(): SubscriptionPlanData {
    val type = allSubscriptionTypes.find { it.name == planName } ?: throw IllegalArgumentException(
        "SubscriptionPlanEntity.toUiModel(): incorrect planName from database"
    )
    val planData = allSubscriptionPlansData.find { it.type == type } ?: throw IllegalArgumentException(
        "SubscriptionPlanEntity.toUiModel(): client has incorrect SubscriptionPlanType"
    )
    return planData
}