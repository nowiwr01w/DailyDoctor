package nowiwr01p.daily.doctor.database.data.storage.onboarding

import com.nowiwr01p.model.model.onboarding.brand_onboardings.getOnboardingItems
import nowiwr01p.daily.doctor.database.domain.storage.onboarding.DatabaseOnboardingStorage

class DatabaseOnboardingStorageImpl: DatabaseOnboardingStorage {

    override suspend fun getOnboardingData() = getOnboardingItems() // TODO: Move to PostgreSQL
}