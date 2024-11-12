package nowiwr01p.daily.doctor.database.data.repository.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItem
import nowiwr01p.daily.doctor.database.domain.repository.onboarding.DatabaseOnboardingRepository
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage

class DatabaseOnboardingRepositoryImpl(
    private val storage: DatabaseOnboardingStorage
): DatabaseOnboardingRepository {

    override suspend fun getOnboardingData(type: BrandConfigType): List<OnboardingItem> {
        return storage.getOnboardingData(type)
    }
}