package nowiwr01p.daily.doctor.database.tables.table.subscription

import nowiwr01p.daily.doctor.database.tables.table.brand.BrandEntity
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object SubscriptionBenefitsTable: UUIDTable("subscription_benefits") {
    val brand = reference("brand_id", BrandTable)
    val languageCode = varchar("language_code", 5)
    val subscriptionPlanId = reference("subscription_plan_id", SubscriptionPlanTable)
    val title = varchar("title", 128)
    val description = text("description")
}

class SubscriptionBenefitsEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var brand by BrandEntity referencedOn SubscriptionBenefitsTable.brand
    var languageCode by SubscriptionBenefitsTable.languageCode
    var subscriptionPlanId by SubscriptionBenefitsTable.subscriptionPlanId
    var title by SubscriptionBenefitsTable.title
    var description by SubscriptionBenefitsTable.description

    companion object: UUIDEntityClass<SubscriptionBenefitsEntity>(SubscriptionBenefitsTable)
}