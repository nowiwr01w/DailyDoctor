package nowiwr01p.daily.doctor.database.init_values.data.brand

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import nowiwr01p.daily.doctor.database.init_values.domain.brand.DatabaseInitBrandsTableUseCase
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseInitBrandsTableUseCaseImpl : DatabaseInitBrandsTableUseCase {

    override suspend fun execute(input: Unit) = transaction {
        val configBrands = BrandConfigType
            .entries
            .map { it.type }
        val existingBrands = BrandTable
            .selectAll()
            .map { it[BrandTable.brandName] }
        if (existingBrands.isEmpty()) {
            insertBrands(configBrands)
        } else {
            synchronizeBrands(configBrands = configBrands, existingBrands = existingBrands)
        }
    }

    private fun synchronizeBrands(
        configBrands: List<String>,
        existingBrands: List<String>
    ) {
        if (existingBrands == configBrands) {
            return
        }

        val brandsToInsert = configBrands.filter { it !in existingBrands }
        val brandsToDelete = existingBrands.filter { it !in configBrands }

        if (brandsToInsert.isNotEmpty()) {
            insertBrands(brandsToInsert)
        }
        if (brandsToDelete.isNotEmpty()) {
            deleteBrands(brandsToDelete)
        }
    }

    private fun insertBrands(brands: List<String>) {
        brands.forEach { brand ->
            BrandTable.insert { it[brandName] = brand }
        }
    }

    private fun deleteBrands(brands: List<String>) {
        brands.forEach { brand ->
            BrandTable.deleteWhere { brandName eq brand }
        }
    }
}