package nowiwr01p.daily.doctor.database.tables.table.subscription

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object SubscriptionBenefitsTable: UUIDTable("subscription_benefits") {
    val languageCode = varchar("language_code", 5)
    val subscriptionPlanId = reference("subscription_plan_id", SubscriptionPlanTable)
    val title = varchar("title", 128)
    val description = text("description")
    val order = integer("order")
}

class SubscriptionBenefitsEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var languageCode by SubscriptionBenefitsTable.languageCode
    var subscriptionPlanId by SubscriptionBenefitsTable.subscriptionPlanId
    var title by SubscriptionBenefitsTable.title
    var description by SubscriptionBenefitsTable.description
    var order by SubscriptionBenefitsTable.order

    companion object: UUIDEntityClass<SubscriptionBenefitsEntity>(SubscriptionBenefitsTable)
}