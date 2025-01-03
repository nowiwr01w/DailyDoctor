package nowiwr01p.daily.doctor.database.tables.table.subscription

import nowiwr01p.daily.doctor.database.tables.table.brand.BrandEntity
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object SubscriptionPlanTable: UUIDTable("subscription_plans") {
    val brand = reference("brand_id", BrandTable)
    val planName = varchar("plan_name", 32)
    val languageCode = varchar("language_code", 5)
    val monthlyPrice = double("monthlyPrice")
    val monthlyPriceDiscounted = double("monthlyPriceDiscounted")
    val yearlyPrice = double("yearlyPrice")
    val yearlyPriceDiscounted = double("yearlyPriceDiscounted")
}

class SubscriptionPlanEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var brand by BrandEntity referencedOn SubscriptionPlanTable.brand
    var planName by SubscriptionPlanTable.planName
    var languageCode by SubscriptionPlanTable.languageCode
    var monthlyPrice by SubscriptionPlanTable.monthlyPrice
    var monthlyPriceDiscounted by SubscriptionPlanTable.monthlyPriceDiscounted
    var yearlyPrice by SubscriptionPlanTable.yearlyPrice
    var yearlyPriceDiscounted by SubscriptionPlanTable.yearlyPriceDiscounted

    companion object: UUIDEntityClass<SubscriptionPlanEntity>(SubscriptionPlanTable)
}