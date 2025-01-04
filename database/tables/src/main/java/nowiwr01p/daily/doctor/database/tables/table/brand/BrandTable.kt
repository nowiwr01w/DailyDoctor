package nowiwr01p.daily.doctor.database.tables.table.brand

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object BrandTable: UUIDTable("brands") {
    val brandName = varchar("name", 255)
}

class BrandEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var brandName by BrandTable.brandName
    companion object: UUIDEntityClass<BrandEntity>(BrandTable)
}