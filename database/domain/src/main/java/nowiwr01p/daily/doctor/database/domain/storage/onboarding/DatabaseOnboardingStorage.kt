package nowiwr01p.daily.doctor.database.domain.storage.onboarding

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface DatabaseOnboardingStorage {
    suspend fun getOnboardingData(type: BrandConfigType): List<OnboardingItemModel>
}