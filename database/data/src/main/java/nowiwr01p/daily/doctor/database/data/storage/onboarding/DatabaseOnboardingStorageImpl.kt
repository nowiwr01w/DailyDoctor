package nowiwr01p.daily.doctor.database.data.storage.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.brand_onboardings.getOnboardingItems
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage

class DatabaseOnboardingStorageImpl: DatabaseOnboardingStorage {

    override suspend fun getOnboardingData(type: BrandConfigType) = getOnboardingItems() // TODO: Move to PostgreSQL
}