package nowiwr01p.daily.doctor.database.data.storage.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItem
import nowiwr01p.daily.doctor.database.data.storage.BaseDatabaseStorage
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage
import nowiwr01p.daily.doctor.database.tables.table.onboarding.OnboardingTable
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseOnboardingStorageImpl: BaseDatabaseStorage(), DatabaseOnboardingStorage {

    override suspend fun getOnboardingData(type: BrandConfigType) = transaction {
        val brandId = getBrandId(type)
        OnboardingTable.selectAll()
            .where {
                val sameBrand = OnboardingTable.brand eq brandId
                val sameLanguage = OnboardingTable.languageCode eq "ru" // TODO: Add Language support
                sameBrand and sameLanguage
            }
            .orderBy(OnboardingTable.position to SortOrder.ASC)
            .map { row ->
                OnboardingItem(
                    image = row[OnboardingTable.image],
                    title = row[OnboardingTable.title],
                    description = row[OnboardingTable.description],
                    firstButtonText = row[OnboardingTable.firstButtonText],
                    secondButtonText = row[OnboardingTable.secondButtonText]
                )
            }
    }
}