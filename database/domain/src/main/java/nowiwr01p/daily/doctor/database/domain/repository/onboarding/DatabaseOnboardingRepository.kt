package nowiwr01p.daily.doctor.database.domain.repository.onboarding

import com.nowiwr01p.model.model.onboarding.OnboardingItemModel

interface DatabaseOnboardingRepository {
    suspend fun getOnboardingData(): List<OnboardingItemModel>
}