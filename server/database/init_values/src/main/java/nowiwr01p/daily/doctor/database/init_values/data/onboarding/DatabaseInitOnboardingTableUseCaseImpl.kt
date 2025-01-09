package nowiwr01p.daily.doctor.database.init_values.data.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemServer
import com.nowiwr01p.model.model.onboarding.allOnboardingItemsWithTranslation
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.DatabaseInitOnboardingTableUseCase
import nowiwr01p.daily.doctor.database.tables.table.onboarding.OnboardingEntity
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseInitOnboardingTableUseCaseImpl: DatabaseInitOnboardingTableUseCase {
    /**
     * INIT ONBOARDINGS
     */
    override suspend fun execute(input: Unit) {
        deleteExistingOnboardingData()
        insertOnboardingData(allOnboardingItemsWithTranslation)
    }

    /**
     * DELETE EXISTING DATA
     */
    private fun deleteExistingOnboardingData() = transaction {
        OnboardingEntity.all().forEach { it.delete() }
    }

    /**
     * ONBOARDING
     */
    private fun insertOnboardingData(allOnboardings: List<OnboardingItemServer>) = transaction {
        allOnboardings.distinctBy { it.type }.forEach { distinctOnboarding ->
            distinctOnboarding.items.forEach { onboardingData ->
                OnboardingEntity.new {
                    languageCode = onboardingData.language.code
                    position = onboardingData.type.position
                    image = onboardingData.itemData.image
                    title = onboardingData.itemData.title
                    description = onboardingData.itemData.description
                    firstButtonText = onboardingData.itemData.firstButtonText
                    secondButtonText = onboardingData.itemData.secondButtonText
                }
            }
        }
    }
}