package nowiwr01p.daily.doctor.database.data.storage

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import org.jetbrains.exposed.sql.selectAll

abstract class BaseDatabaseStorage {
    /**
     * BRAND
     */
    protected fun getBrandId(type: BrandConfigType) = BrandTable.selectAll()
        .where { BrandTable.brandName eq type.type }
        .map { row -> row[BrandTable.id] }
        .first()
}