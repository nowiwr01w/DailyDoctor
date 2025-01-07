package nowiwr01p.daily.doctor.database.data.storage.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.model.onboarding.item.OnboardingItemData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.OnlineAppointment
import com.nowiwr01p.model.model.onboarding.type.allOnboardingTypes
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.data.storage.BaseDatabaseStorage
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage
import nowiwr01p.daily.doctor.database.tables.table.onboarding.OnboardingEntity
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseOnboardingStorageImpl: BaseDatabaseStorage(), DatabaseOnboardingStorage {
    /**
     * ONBOARDINGS
     */
    override suspend fun getOnboardingData(language: Language) = transaction {
        OnboardingEntity.all()
            .filter { entity ->
                entity.languageCode == language.code
            }
            .sortedBy { entity ->
                entity.position
            }
            .map { entity ->
                OnboardingItem(
                    type = allOnboardingTypes.find { it.position == entity.position } ?: OnlineAppointment,
                    data = OnboardingItemData(
                        image = entity.image,
                        title = entity.title,
                        description = entity.description,
                        firstButtonText = entity.firstButtonText,
                        secondButtonText = entity.secondButtonText
                    )
                )
            }
    }
}