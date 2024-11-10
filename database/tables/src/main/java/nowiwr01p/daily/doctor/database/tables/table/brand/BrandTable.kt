package nowiwr01p.daily.doctor.database.tables.table.brand

import org.jetbrains.exposed.dao.id.UUIDTable

object BrandTable: UUIDTable("brands") {
    val brandName = varchar("name", 255)
}