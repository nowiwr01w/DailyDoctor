package nowiwr01p.daily.doctor.database.domain.storage.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface DatabaseOnboardingStorage {
    suspend fun getOnboardingData(): List<OnboardingItemModel>
}